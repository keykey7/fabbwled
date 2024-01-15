package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section520 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(520);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        You hold up your arms and utter the mightiest prayer that you
                        know. It has no effect.
                        The three white figures pelt you with a barrage of plates and
                        knives. Something heavy hits you on the side of the head and
                        you fall with a groan. The ghosts take advantage of this to snatch
                        up the casket of silver and run off. Lose 2 Stamina points.
                        """)
                .clickable(a -> a.addStamina(-2), b -> b.text("You loose 2 Stamina points.")
                        .activeIf(current.doIStillLive(), c -> c
                                .choice(d -> d.text("Try to track them down"),
                                        d -> d.clickableTurnTo(541))
                                .choice(d -> d.text("Follow the river north"),
                                        d -> d.clickableTurnTo(576))
                                .choice(d -> d.text("Follow the river south"),
                                        d -> d.clickableTurnTo(82))
                                .choice(d -> d.text("Head east into the countryside"),
                                        d -> d.clickableTurnTo(278))
                                .choice(d -> d.text("West to the main road"),
                                        d -> d.clickableTurnTo(558))
                        )
                        .activeElse(d -> d
                                .text("You died. Start from the beginning!")
                                .clickableTurnTo(1)));
    }
}
