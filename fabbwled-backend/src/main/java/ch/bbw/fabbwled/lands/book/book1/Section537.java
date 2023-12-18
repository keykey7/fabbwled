package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section537 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(537);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        You find out that they call themselves the Gypsies of the Sea,
                        wandering nomads of the waves, who find their islands naturally
                        occurring in the Sea of Weeds. They make their homes upon
                        them, and live a life of aimless drifting.
                        A tanned young man, full of wit and charm, explains that he
                        makes his living from pearl diving, and selling what he finds to
                        people like yourself. He offers you a bag of pearls for 25
                        Shards..
                        """)
                .activeIf(current.hasEnoughShards(25), a -> a
                        .choice(c -> c.text("Buy the pearls"),
                                d -> d.clickable(e -> e.addShards(-25).withCurrentSectionId(106),
                                        f -> f.text("Pay 25 Shards and turn to 106")).text(".\n")))
                .choice(c -> c.text("Leave and resume your journey"),
                        d -> d.clickableTurnTo(85))

                .activeElse(a -> a
                        .text("You don't have enough shards. Leave and resume your journey.")
                        .clickableTurnTo(85)
                        .text(".")
                );
    }
}
