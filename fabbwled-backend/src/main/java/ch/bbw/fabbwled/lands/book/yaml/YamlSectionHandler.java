package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.exception.FabledBusinessException;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.function.Function;

@RequiredArgsConstructor
public class YamlSectionHandler implements SectionHandler {
    private final PlayerSession playerSession;
    private final YamlSection section;

    private ArrayList<Function<PlayerSession.PlayerDto, PlayerSession.PlayerDto>> handlers = new ArrayList<>();

    @Override
    public SectionId getId() {
        return section.id();
    }

    @Override
    public SectionNode getBody() {
        var node = SectionNode.root();

        var writer = new YamlSectionWriter(playerSession, section.id());

        for (Action action : section.actions()) {
            node = action.writeToNode(writer, node);
        }

        handlers = writer.getClickHandlers();

        return node;
    }

    @Override
    public void onClick(int id) {
        if (this.handlers == null) {
            throw new FabledBusinessException("Cannot click yet");
        }
        if (id >= this.handlers.size()) {
            throw new FabledBusinessException("Click index out of range");
        }
        var handler = this.handlers.get(id);
        playerSession.update(handler::apply);
    }
}
