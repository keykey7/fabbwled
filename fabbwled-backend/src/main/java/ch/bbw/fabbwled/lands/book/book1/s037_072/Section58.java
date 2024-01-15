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
public class Section58 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 58);
    }

    /** ISNOTIMPLEMENTABLE: get Possession */
    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        He doesn’t notice you hiding in the shadowy doorway of a
                        nearby derelict house. As he passes, you step out and attack,
                        taking him completely by surprise. He goes down with your first
                        blow. Searching him, you find 25 Shards, which you can take if
                        you wish. Then you flip up his eyepatch. Nestling in the eye
                        socket is a sparkling gem, a flame opal. Quickly, you haul the
                        body into the shadows and head for the city centre.""")
                .choice(c -> c.text("25 Shards"), g -> g.clickable(p -> p.addShards(25).withVolatileSectionStore((int) p.volatileSectionStore() + 1), x -> x.text("Get shards")))
                .clickableTurnTo(400);
    }
}
