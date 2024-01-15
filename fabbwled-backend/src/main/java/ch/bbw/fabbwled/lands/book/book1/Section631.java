package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section631 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(631);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        The soldier nods. Suddenly, he shouts something and several
                        archers pop up from behind rocks and start shooting at you. An
                        arrow embeds itself in your shoulder.
                        """)
                .clickable(b -> b.addStamina(-3), a -> a.text("Lose 3 Stamina points."))
                .text("As you climb down, you are shot at again.")
                .activeIf(current.doIStillLive(), a -> a.text("You realize you are a sitting duck, and you run for your life.")
                        .choice(b -> b.text("Lose 6 Stamina"), c -> c.clickable(d -> d.addStamina(-6), e -> e.text("Lose 6 Stamina")))
                        .choice(b -> b.text("Lose 3 Stamina"), c -> c.clickable(d -> d.addStamina(-3), e -> e.text("Lose 3 Stamina")))
                        .choice(b -> b.text("You are unwounded"), c -> c.clickable(d -> d.addStamina(0), e -> e.text("You are unwounded"))))
                .clickableTurnTo(474);
    }
}
