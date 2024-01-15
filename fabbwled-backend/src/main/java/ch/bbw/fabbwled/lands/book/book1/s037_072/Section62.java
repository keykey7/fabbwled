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
public class Section62 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 62);
    }

    /** ISNOTIMPLEMENTABLE: get Possession */
    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        The Sokarans surrender. Their captain has been killed in the
                        battle, and his marines have had the fight knocked out of them.
                        The war galley isn’t carrying any cargo, but you find an
                        officer’s pass on the body of the captain, and a chest in his
                        cabin, which contains 150 Shards. You have to hand out 50
                        Shards to your crew, but you can add 100 Shards to your
                        Adventure Sheet. You leave the galley to limp back to
                        Yellowport while you sail on.""")
                .choice(c -> c.text("100 Shards"), g -> g.clickable(p -> p.addShards(100).withVolatileSectionStore((int) p.volatileSectionStore() + 1), x -> x.text("Get shards")))
                .clickableTurnTo(439);
    }
}
