package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;

import java.util.List;
import java.util.Optional;

public interface Action {
    void simpleVerify();

    record TextAction(String text) implements Action {
        @Override
        public void simpleVerify() {}
    }

    record IfAction(Condition condition, List<Action> then, Optional<List<Action>> else_) implements Action {
        @Override
        public void simpleVerify() {
            then.forEach(Action::simpleVerify);
            else_.ifPresent(else_ -> else_.forEach(Action::simpleVerify));
        }
    }

    record TurnToAction(SectionId sectionId) implements Action {
        @Override
        public void simpleVerify() {}
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

        record SingleChoice(String text, List<Action> actions) {}
    }
}
