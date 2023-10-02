package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;

import java.util.List;
import java.util.Optional;

public interface Action {
    void simpleVerify();

    YamlReachabilityResult verifyReachability();

    SectionNode.ContainerNode writeToNode(YamlSectionWriter writer, SectionNode.ContainerNode parent);

    record TextAction(String text) implements Action {
        @Override
        public void simpleVerify() {}

        @Override
        public YamlReachabilityResult verifyReachability() {
            return YamlReachabilityResult.NORMAL;
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
            var condition = condition().isActive(writer.getSession());

            parent = parent.activeIf(condition, x -> writer.writeList(this.then, x));
            if (else_.isPresent()) {
                parent = parent.activeIf(!condition, x -> writer.writeList(this.else_.get(), x));
            }
            return parent;
        }
    }

    record TurnToAction(SectionId sectionId) implements Action {
        @Override
        public void simpleVerify() {}

        @Override
        public YamlReachabilityResult verifyReachability() {
            // turnTo always goes to a different action, so it terminates.
            return YamlReachabilityResult.TERMINATES;
        }

        @Override
        public SectionNode.ContainerNode writeToNode(YamlSectionWriter writer, SectionNode.ContainerNode parent) {
            var id = writer.addHandler(player -> player.withCurrentSection(sectionId));
            return parent.clickableTurnTo(id, sectionId.sectionId());
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

            choices.forEach(choice -> choice.actions.forEach(Action::simpleVerify));
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

            }
        }

        record SingleChoice(String text, List<Action> actions) {}
    }
}
