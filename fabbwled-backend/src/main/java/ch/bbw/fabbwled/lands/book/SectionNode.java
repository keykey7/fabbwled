package ch.bbw.fabbwled.lands.book;

import lombok.NonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A tree-structure for the visual (!) representation of a section-body. Think of HTML but without the XML part :) The
 * whole JSON-representation gets quite verbose, but is easy to parse.
 */
public interface SectionNode {

	static ContainerNode root() {
		return ContainerNode.empty();
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

	default Set<Integer> allClickIds() {
		return switch (getType()) {
			case SIMPLE -> Collections.emptySet();
			case CLICKABLE -> Collections.singleton(((ClickableNode) this).clickId()); // not supporting nested clicks
			case CONTAINER -> ((ContainerNode) this).children()
					.stream()
					.map(SectionNode::allClickIds)
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

	record ContainerNode(ContainerStyle style, @NonNull List<SectionNode> children) implements SectionNode {

		static ContainerNode empty() {
			return empty(ContainerStyle.NONE);
		}

		static ContainerNode empty(@NonNull ContainerStyle style) {
			return new ContainerNode(style, Collections.emptyList());
		}

		@Override
		public NodeType getType() {
			return NodeType.CONTAINER;
		}

		ContainerNode append(SectionNode child) {
			return new ContainerNode(style, Stream.concat(children.stream(), Stream.of(child)).toList());
		}

		ContainerNode append(UnaryOperator<ContainerNode> children) {
			return append(children.apply(empty()));
		}

		/**
		 * @param text append this plain-text to the current node
		 */
		public ContainerNode text(String text) {
			return append(SimpleNode.text(text));
		}

		/**
		 * a shortform for the commen "turn to XY"-action
		 *
		 * @see #clickable(int, UnaryOperator)
		 */
		public ContainerNode clickableTurnTo(int clickId, int section) {
			return clickable(clickId, x -> x.text("turn to ").section(section));
		}

        public ContainerNode clickableDice(int clickId, int dice) {
            return clickable(clickId, x -> x.text("Roll a die"));
        }

        public ContainerNode clickableDiceOption(int clickId, int min, int max) {
            return clickable(clickId, x -> x.text("Score " + min + "-" + max));
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
		 * @param clickId  per-section-unique id, will be used later to reference the clicked action
		 * @param children content of the click (like what's inside an {@code <a>-tag}.
		 */
		public ContainerNode clickable(int clickId, UnaryOperator<ContainerNode> children) {
			return append(new ClickableNode(clickId, children.apply(ContainerNode.empty())));
		}

		/**
		 * @param condition if {@code false}, this section is drawn as disabled (unclickable or greyed out)
		 * @param children  the content (all the grey text)
		 */
		public ContainerNode activeIf(boolean condition, UnaryOperator<ContainerNode> children) {
			var style = condition ? ContainerStyle.NONE : ContainerStyle.DISABLED;
			return append(children.apply(empty(style)));
		}

		/**
		 * A choice is usually at the end of a section and has a left-aligned description and a right-aligned action.
		 *
		 * @param left  a description like "Step out of the way"
		 * @param right an action like "turn to 44"
		 * @return the new node
		 */
		public ContainerNode choice(UnaryOperator<ContainerNode> left,
									UnaryOperator<ContainerNode> right) {
			return append(empty(ContainerStyle.CHOICE).append(left.apply(empty())).append(right.apply(empty())));
		}

		enum ContainerStyle {
			/**
			 * No special style, simply a container for {@code #children}. Similar to a {@code <div>-tag}.
			 */
			NONE,
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

	record ClickableNode(int clickId, SectionNode child) implements SectionNode {

		@Override
		public NodeType getType() {
			return NodeType.CLICKABLE;
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

		public static SimpleNode ability(String abilityName) {
			return new SimpleNode(SimpleStyleType.ABILITY, abilityName);
		}

		public static SimpleNode tickbox(boolean active) {
			return new SimpleNode(SimpleStyleType.TICKBOX, String.valueOf(active));
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
