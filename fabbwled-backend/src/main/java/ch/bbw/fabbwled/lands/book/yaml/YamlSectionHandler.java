package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class YamlSectionHandler implements SectionHandler {
    private final PlayerSession playerSession;
    private final YamlSection section;

    @Override
    public SectionId getId() {
        return section.id();
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        var node = SectionNode.root();

        var writer = new YamlSectionWriter(playerSession, section.id());

        for (Action action : section.actions()) {
            node = action.writeToNode(writer, node);
        }

        return node;
    }
}
