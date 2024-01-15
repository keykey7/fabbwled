package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section514 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(514);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        To renounce the worship of Tyrnai, you must pay 50 Shards to
                        the warrior priests, and suffer the ‘Wrathful Blow’. A priest will
                        strike you once – it is better to be struck by a priest than by
                        Tyrnai himself!
                        If you are determined to renounce your initiate status, pay
                        the 50 Shards and delete Tyrnai from the God box on your
                        Adventure Sheet.
                        """)
                .activeIf(current.hasEnoughShards(50), a -> a
                        .choice(c -> c.text("Pay the 50 Shards"), g -> g.clickable(p -> p.addShards(-50), x -> x.text("""
                                If you are determined to renounce your initiate status, pay
                                the 50 Shards and delete Tyrnai from the God box on your
                                Adventure Sheet. The high priest smashes you across the jaw,
                                saying ‘I’m doing you a favour – believe me!’
                                """).clickable(f ->
                                        f.addStamina(-1),
                                b -> b.text("You loose 1 Stamina point.")
                                        .activeIf(current.doIStillLive(), c -> c
                                                .clickableTurnTo(282)
                                                .activeElse(d -> d
                                                        .text("You died. Start from the beginning!")
                                                        .clickableTurnTo(1)))
                        ))))
                .activeElse(i -> i.clickableTurnTo(282));
    }
}
