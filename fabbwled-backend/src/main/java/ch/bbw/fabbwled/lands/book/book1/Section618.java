package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section618 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(618);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        Becoming an initiate of Lacuna gives you the benefit of paying
                        less for blessings and other services the temple can offer. It costs
                        30 Shards to become an initiate. You cannot do this if you are
                        already an initiate of another temple.
                        """)
                .activeIf(!current.god().isEmpty(), a -> a.text("Become an initiate of Lacuna")
                        .choice(b -> b.text("Become an initiate and pay 30 Shards"), c -> c.clickable(playerDto -> playerDto.withShards(current.shards() - 30).withGod("Lacuna"), d -> d.text("Become an initiate and pay 30 Shards")))
                        .clickableTurnTo(544));
    }
}
