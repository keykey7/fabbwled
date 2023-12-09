package ch.bbw.fabbwled.lands.book;

import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NonNull;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A tree-structure for the visual (!) representation of a section-body. Think of HTML but without the XML part :) The
 * whole JSON-representation gets quite verbose, but is easy to parse.
 */
public interface SectionNode {

    interface PlayerChange extends UnaryOperator<PlayerDto> {
    }

	static ContainerNode root() {
		return ContainerNode.root();
	}

	NodeType getType();

	default String asPlainText() {
		return switch (getType()) {
			case SIMPLE -> ((SimpleNode) this).text();
			case CLICKABLE -> ((ClickableNode) this).child().asPlainText(); // not supporting nested clicks
			case CONTAINER -> ((ContainerNode) this).children()
					.stream()
					.map(SectionNode::asPlainText)
					.collect(Collectors.joining());
		};
	}

	default Set<ClickableNode> allActiveClickIds() {
		return switch (getType()) {
			case SIMPLE -> Collections.emptySet();
			case CLICKABLE -> Collections.singleton((ClickableNode) this); // not supporting nested clicks
			case CONTAINER -> Stream.of((ContainerNode) this)
                    .filter(x -> x.style != ContainerNode.ContainerStyle.DISABLED)
                    .flatMap(x -> x.children.stream())
					.map(SectionNode::allActiveClickIds)
					.flatMap(Collection::stream)
					.collect(Collectors.toMap(x -> x, x -> 0))
					.keySet(); // toMap().keySet() instead of toSet() to crash on duplicates
		};
	}

	enum NodeType {
		/**
		 * a node with multiple child nodes
		 */
		CONTAINER,
		/**
		 * a clickable node (similar to an {@code <a href="...">} tag
		 */
		CLICKABLE,
		/**
		 * a simple text-node without child nodes
		 */
		SIMPLE,
	}

    record ContainerNode(@JsonIgnore AtomicInteger clickId, ContainerStyle style, @NonNull List<SectionNode> children) implements SectionNode {

		public static ContainerNode root() {
            return new ContainerNode(new AtomicInteger(0), ContainerStyle.NONE, new ArrayList<>());
        }

		public ContainerNode empty() {
            return empty(ContainerStyle.NONE);
		}

        ContainerNode empty(@NonNull ContainerStyle style) {
            return new ContainerNode(clickId, style, new ArrayList<>());
        }

		@Override
		public NodeType getType() {
			return NodeType.CONTAINER;
		}

		@Override
		public String asPlainText() {
			return switch (style) {
                case NONE, ENABLED -> children()
						.stream()
						.map(SectionNode::asPlainText)
						.collect(Collectors.joining());
				case DISABLED -> "\033[37m" + children()
						.stream()
						.map(SectionNode::asPlainText)
						.collect(Collectors.joining()) + "\033[39m"; // grey FG
				case CHOICE -> "\t" + children.get(0).asPlainText() + "\t" + children.get(1).asPlainText() + "\n";
			};
		}

		public ContainerNode append(SectionNode child) {
            children.add(child);
            return this;
		}

		/**
		 * @param text append this plain-text to the current node
		 */
		public ContainerNode text(String text) {
			return append(SimpleNode.text(text));
		}

		/**
		 * a shortform for the commen "turn to XY"-action
		 */
		public ContainerNode clickableTurnTo(int section) {
            return clickable(playerDto -> playerDto.withCurrentSectionId(section), x -> x.text("turn to ").section(section));
		}

        public ContainerNode clickableRollDice(int dice) {
            if (dice == 1 ) {
                return clickable(x -> x.withDiceRoll(dice), x -> x.text("Roll a die"));
            }
           return clickable(x -> x.withDiceRoll(dice), x -> x.text("Roll " + dice + " dices"));
        }

		/**
		 * @param id the section-id (usually redered in bold)
		 */
		public ContainerNode section(int id) {
			return append(SimpleNode.section(id));
		}

		/**
		 * @param itemName an item description (usually rendered in bold)
		 */
		public ContainerNode item(String itemName) {
			return append(SimpleNode.item(itemName));
		}

        /**
         * @param action what would change if the player would click on it
         * @param child  content of the click like what's inside an {@code <a>-tag}.
         */
        public ContainerNode clickable(PlayerChange action, Consumer<ContainerNode> child) {
            var childNode = empty();
            child.accept(childNode);
            return append(new ClickableNode(action, clickId.getAndIncrement(), childNode));
        }

		/**
		 * @param condition if {@code false}, this section is drawn as disabled (unclickable or greyed out)
		 * @param child  the content (all the grey text)
         * @see #activeElseIf(boolean, Consumer) 
         * @see #activeElse(Consumer) 
		 */
		public ContainerNode activeIf(boolean condition, Consumer<ContainerNode> child) {
            var childNode = empty(condition ? ContainerStyle.ENABLED : ContainerStyle.DISABLED);
            child.accept(childNode);
			return append(childNode);
		}

        private boolean isPreviousIfActive() {
            var previous = children.stream().filter(ContainerNode.class::isInstance)
                    .map(x -> (ContainerNode) x)
                    .filter(x -> List.of(ContainerStyle.ENABLED, ContainerStyle.DISABLED).contains(x.style))
                    .toList();
            if (previous.isEmpty()) {
                throw new IllegalStateException("cannot construct an if-else without previous if");
            }
            return previous.stream().anyMatch(x -> x.style == ContainerStyle.ENABLED);
        }

        public ContainerNode activeElseIf(boolean condition, Consumer<ContainerNode> child) {
            return activeIf(!isPreviousIfActive() && condition, child);
        }

        public ContainerNode activeElse(Consumer<ContainerNode> child) {
            return activeIf(!isPreviousIfActive(), child);
        }

        /**
         * Full difficulty dialog like:
         * Make a ??? roll at Difficulty ??<br>
         * Successful ??? roll (onSuccess)<br>
         * Failed ??? roll (onFailure)
         */
        public ContainerNode clickableDifficultyRollWithOptions(PlayerDto current, AbilityEnum type, int difficulty,
                                                                Consumer<ContainerNode> onSuccess,
                                                                Consumer<ContainerNode> onFailure) {
            return clickableDifficultyRoll(current, type, difficulty)
                    .text(".\n")
                    .activeIfDifficultySuccess(current, difficulty, x -> x
                            .choice(l -> l.text("Successful ").append(SimpleNode.ability(type)).text(" roll"), onSuccess))
                    .activeElseIf(current.lastDifficultyRoll() != null, x -> x
                            .choice(l -> l.text("Failed ").append(SimpleNode.ability(type)).text(" roll"), onFailure));
        }

        /**
         * Standard dialog "Make a ??? roll at Difficulty ??".
         * Note: this isn't good enough if there are multiple difficulty rolls...
         */
        public ContainerNode clickableDifficultyRoll(PlayerDto current, AbilityEnum type, int difficulty) {
            return append(empty().activeIf(current.lastDifficultyRoll() != type, a -> a
                    .clickable(player -> player.withDiceRoll(2).withLastDifficultyRoll(type), b -> b
                            .text("Make a ")
                            .append(SimpleNode.ability(type))
                            .text(" roll at Difficulty " + difficulty))));
        }

        /**
         * selectable on successful {@code #clickableDifficultyRoll}.
         */
        public ContainerNode activeIfDifficultySuccess(PlayerDto current, int difficulty, Consumer<ContainerNode> child) {
            return activeIf(current.lastDifficultyRoll() != null &&
                    current.getLastRollSum() + current.baseStats().getByType(current.lastDifficultyRoll()) > difficulty, child);
        }

		/**
		 * A choice is usually at the end of a section and has a left-aligned description and a right-aligned action.
		 *
		 * @param left  a description like "Step out of the way"
		 * @param right an action like "turn to 44"
		 * @return the new node
		 */
		public ContainerNode choice(Consumer<ContainerNode> left,
                                    Consumer<ContainerNode> right) {
            var leftNode = empty();
            left.accept(leftNode);
            var rightNode = empty();
            right.accept(rightNode);
			return append(empty(ContainerStyle.CHOICE).append(leftNode).append(rightNode));
		}

		enum ContainerStyle {
			/**
			 * No special style, simply a container for {@code #children}. Similar to a {@code <div>-tag}.
			 */
			NONE,
            /**
             * Similar to NONE, but explicitly enabled.
             */
            ENABLED,
			/**
			 * All child nodes are disabled/deactivated and cannot be interacted with
			 */
			DISABLED,
			/**
			 * Two-part selection, see {@code #choice()}
			 */
			CHOICE,
			// TODO 27-Aug-2023/mvk: add more styles like ENEMY here...
		}
	}

	record ClickableNode(@JsonIgnore PlayerChange playerChange, int clickId, SectionNode child) implements SectionNode {

		@Override
		public NodeType getType() {
			return NodeType.CLICKABLE;
		}

		@Override
		public String asPlainText() {
			return "\033[4m" + child.asPlainText() + " [" + clickId + "]\033[24m"; // underlined
		}
	}

	record SimpleNode(SimpleStyleType style, String text) implements SectionNode {
		public static SimpleNode text(String text) {
			return new SimpleNode(SimpleStyleType.NONE, text);
		}

		public static SimpleNode section(int id) {
			return new SimpleNode(SimpleStyleType.SECTION, String.valueOf(id));
		}

		public static SimpleNode item(String itemName) {
			return new SimpleNode(SimpleStyleType.ITEM, itemName);
		}

		public static SimpleNode ability(AbilityEnum type) {
			return new SimpleNode(SimpleStyleType.ABILITY, type.name());
		}

		public static SimpleNode tickbox(boolean active) {
			return new SimpleNode(SimpleStyleType.TICKBOX, String.valueOf(active));
		}

		@Override
		public String asPlainText() {
			return switch (style) {
				case NONE -> text;
				case SECTION -> "\033[1m" + text + "\033[22m"; // bold
				case ABILITY -> text.toUpperCase();
				case ITEM -> "\033[3m" + text + "\033[23m"; // italic
				case TICKBOX -> "true".equals(text) ? "☒" : "☐";
			};
		}

		@Override
		public NodeType getType() {
			return NodeType.SIMPLE;
		}

		enum SimpleStyleType {
			/**
			 * a simple text-node
			 */
			NONE,
			/**
			 * a section-number: in the books they are written in bold
			 */
			SECTION,
			/**
			 * an item description: in the books they are written in bold
			 */
			ITEM,
			/**
			 * an ability name: in the books they are capitalized
			 */
			ABILITY,
			/**
			 * an inline tickbox similar to tick-boxes on section-title-level
			 */
			TICKBOX,
			// TODO 27-Aug-2023/mvk: add more styles like table-title,...
		}
	}
}
