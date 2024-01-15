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
public class Section68 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 68);
    }

    /** ISNOTIMPLEMENTABLE: get Possession */
    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        To renounce the worship of Alvir and Valmir, you must pay 30
                        Shards in compensation to the priesthood.
                        The priest simply points to a ship limping into harbour – its
                        shattered masts, torn sails and battered hull mute testimony to its
                        storm-tossed voyage.
                        ‘The captain did not revere the Twin Gods,’ whispers the
                        coral-jewelled priest darkly.
                        """)
                .choice(c -> c.text("lose 30 Shards"), g -> g.clickable(p -> p.addShards(-30).withVolatileSectionStore((int) p.volatileSectionStore() + 1), x -> x.text("Lose shards")))
                .clickableTurnTo(154);
    }
}
