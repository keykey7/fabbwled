package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;

import java.util.List;
import java.util.Optional;

public interface Action {
    default void simpleVerify() {
    }

    default YamlReachabilityResult verifyReachability() {
        return YamlReachabilityResult.NORMAL;
    }

    /**
     * Render the YAML action into a section node.
     *
     * @param writer The context of the current rendering session. Contains the player session and handlers.
     * @param parent The parent section node where the child nodes get appended to.
     * @return The new resulting node.
     */
    SectionNode.ContainerNode writeToNode(YamlSectionWriter writer, SectionNode.ContainerNode parent);

    record TextAction(String text) implements Action {
        @Override
        public void simpleVerify() {
            // Avoid checking, it's okay to have double spaces for now.
            //noinspection PointlessBooleanExpression
            if (false && this.text().contains("  ")) {
                throw new FabledTechnicalException("Text contains double spaces. Replace all double spaces with a single space.");
            }
        }

        @Override
        public SectionNode.ContainerNode writeToNode(YamlSectionWriter writer, SectionNode.ContainerNode parent) {
            return parent.text(text);
        }
    }

    record IfAction(Condition condition, List<Action> then, Optional<List<Action>> else_) implements Action {
        @Override
        public void simpleVerify() {
            then.forEach(Action::simpleVerify);
            else_.ifPresent(else_ -> else_.forEach(Action::simpleVerify));
        }

        @Override
        public YamlReachabilityResult verifyReachability() {
            var thenResult = YamlActionReachabilityHelper.verifyActionList(then);
            if (else_.isPresent()) {
                thenResult = thenResult.intersection(YamlActionReachabilityHelper.verifyActionList(else_.get()));
            }
            return thenResult;
        }

        @Override
        public SectionNode.ContainerNode writeToNode(YamlSectionWriter writer, SectionNode.ContainerNode parent) {
            var condition = this.condition().isActive(writer.getSession(), writer.getSectionId());

            parent = parent.activeIf(condition, x -> writer.writeList(this.then, x));
            if (else_.isPresent()) {
                parent = parent.activeIf(!condition, x -> writer.writeList(this.else_.get(), x));
            }
            return parent;
        }
    }

    record TurnToAction(SectionId sectionId) implements Action {
        @Override
        public void simpleVerify() {
            Action.super.simpleVerify();
        }

        @Override
        public YamlReachabilityResult verifyReachability() {
            // turnTo always goes to a different action, so it terminates.
            return YamlReachabilityResult.TERMINATES;
        }

        @Override
        public SectionNode.ContainerNode writeToNode(YamlSectionWriter writer, SectionNode.ContainerNode parent) {
            return parent.clickableTurnTo(sectionId.sectionId());
        }
    }

    record AcquireKeywordAction(String keyword) implements Action {
        @Override
        public SectionNode.ContainerNode writeToNode(YamlSectionWriter writer, SectionNode.ContainerNode parent) {
            return parent.clickable(player -> player.addCodeWord(keyword), node -> node.text("Acquire keyword " + keyword));
        }
    }


    record AcquirePosessionAction(String possession) implements Action {
        @Override
        public SectionNode.ContainerNode writeToNode(YamlSectionWriter writer, SectionNode.ContainerNode parent) {
            return parent.clickable(player -> player.addPossession(possession), node -> node.text("Acquire possession " + possession));
        }
    }

    record CheckTickBoxAction(boolean set) implements Action {
        @Override
        public SectionNode.ContainerNode writeToNode(YamlSectionWriter writer, SectionNode.ContainerNode parent) {
            return parent.clickable(PlayerDto::addTick, node -> node.text("Tick one more box."));
        }
    }

    record UseResurrectionDealAction(boolean useResurrectionDeal) implements Action {
        @Override
        public SectionNode.ContainerNode writeToNode(YamlSectionWriter writer, SectionNode.ContainerNode parent) {
            writer.getSession().update(x -> x.withResurrectionPossible(false));
            return parent.clickableTurnTo(writer.getSession().getPlayer().resurrectionArrangement().sectionIdToTurnTo().sectionId());
        }
    }

    record SpendShardsAction(int amount) implements Action {
        @Override
        public SectionNode.ContainerNode writeToNode(YamlSectionWriter writer, SectionNode.ContainerNode parent) {
            var msg = amount > 0 ? ("Buy for " + amount + " shards") : ("Get " + -amount + " shards");
            var canBuy = writer.getSession().getPlayer().shards() > amount;
            return parent.activeIf(canBuy,
                    node -> node.clickable(player -> player.addShards(-amount), node1 -> node1.text(msg)));
        }
    }

    record Choice(List<SingleChoice> choices) implements Action {
        @Override
        public void simpleVerify() {
            if (choices.isEmpty()) {
                throw new FabledTechnicalException("Found choice with no choices, this doesn't make sense.");
            }
            if (choices.size() == 1) {
                throw new FabledTechnicalException("Choice action only has a single choice, don't use a choice.");
            }

            choices.forEach(choice -> {
                if (choice.actions.size() != 1) {
                    throw new FabledTechnicalException("Choice actions must contain a single turnTo");
                }
                if (!(choice.actions.get(0) instanceof Action.TurnToAction)) {
                    throw new FabledTechnicalException("Choice actions must contain a single turnTo");
                }
                choice.actions.forEach(Action::simpleVerify);
            });
        }

        @Override
        public YamlReachabilityResult verifyReachability() {
            var reachability = YamlReachabilityResult.TERMINATES;

            for (SingleChoice choice : choices) {
                reachability =
                        reachability.intersection(YamlActionReachabilityHelper.verifyActionList(choice.actions()));
            }

            return reachability;
        }

        @Override
        public SectionNode.ContainerNode writeToNode(YamlSectionWriter writer, SectionNode.ContainerNode parent) {
            for (SingleChoice choice : choices) {
                var turnTo = (Action.TurnToAction) choice.actions.get(0);
                parent = parent.choice(c -> c.text(choice.text),
                                       a -> a.clickableTurnTo(turnTo.sectionId.sectionId())
                );
            }
            return parent;
        }

        record SingleChoice(String text, List<Action> actions) {
        }
    }
}
