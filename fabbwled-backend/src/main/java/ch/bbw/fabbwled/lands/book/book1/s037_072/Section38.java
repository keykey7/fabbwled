package ch.bbw.fabbwled.lands.book.book1.s037_072;


import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class Section38 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 38);
    }

    /** ISNOTIMPLEMENTABLE: more than 3 dices*/
    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        Heavy black clouds race towards you across the sky, whipping
                        the waves into a frenzy. The crew mutter among themselves
                        fearfully.
                        Otherwise the storm hits with full fury.""")
                .activeIf(current.possessions().contains("blessing of Alvir and Valmir"), c -> c.clickableTurnTo(209))
                .activeElse(x -> x.clickableTurnTo(209));
    }
}
