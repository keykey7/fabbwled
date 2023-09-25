package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.book.SectionId;

import java.util.List;
import java.util.Optional;

public interface Action {
    record TextAction(String text) implements Action {}

    record IfAction(Condition condition, List<Action> then, Optional<List<Action>> else_) implements Action {}

    record TurnToAction(SectionId sectionId) implements Action {}

    record Choice(List<SingleChoice> choices) implements Action {
        record SingleChoice(String text, List<Action> actions) {}
    }
}
