package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.book.SectionId;

import java.util.List;

public record YamlSection(SectionId id, List<Action> actions) {
    public void simpleVerify() {
        this.actions.forEach(Action::simpleVerify);
    }
}
