package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class YamlSectionWriter {
    @Getter
    private PlayerSession session;
    private ArrayList<Function<PlayerSession.PlayerDto, PlayerSession.PlayerDto>> clickHandlers = new ArrayList<>();

    public int addHandler(Function<PlayerSession.PlayerDto, PlayerSession.PlayerDto> handler) {
        var id = clickHandlers.size();
        clickHandlers.add(handler);
        return id;
    }

    public SectionNode.ContainerNode writeList(List<Action> actions, SectionNode.ContainerNode parent) {
        for (Action action : actions) {
            parent = action.writeToNode(this, parent);
        }
        return parent;
    }
}
