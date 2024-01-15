package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section617 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(617);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        A desperate battle ensues.
                        Ghoul, COMBAT 3, Defence 7, Stamina 15
                        If you win, turn  to  196. If you lose, your adventures are over, unless you have a resurrection
                        deal.
                        """)
                .choice(a -> a.text("Win the fight"), b -> b.clickableTurnTo(196))
                .choice(a -> a.text("Lose the fight"), b -> b.clickableTurnTo(196) /* Death not implemented*/);

    }
}
