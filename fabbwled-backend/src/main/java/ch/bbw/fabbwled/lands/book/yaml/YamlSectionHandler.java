package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;

public class YamlSectionHandler implements SectionHandler {
    private final YamlSection section;

    public YamlSectionHandler(YamlSection section) {
        this.section = section;
    }

    @Override
    public SectionId getId() {
        return section.id();
    }

    @Override
    public SectionNode getBody() {
        var node = SectionNode.root();

        for (Action action : section.actions()) {
            if (action instanceof Action.TextAction) {
                node.text(((Action.TextAction) action).text());
            } else if (action instanceof Action.IfAction) {
                ((Action.IfAction) action).condition();
            } else {
                throw new FabledTechnicalException("Cannot deal with action of type " + action);
            }
        }

        return null;
    }

    @Override
    public void onClick(int id) {

    }
}
