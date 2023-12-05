package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class YamlSectionWriter {
    private final PlayerSession session;
    private final SectionId sectionId;

    public SectionNode.ContainerNode writeList(List<Action> actions, SectionNode.ContainerNode parent) {
        for (Action action : actions) {
            parent = action.writeToNode(this, parent);
        }
        return parent;
    }
}
