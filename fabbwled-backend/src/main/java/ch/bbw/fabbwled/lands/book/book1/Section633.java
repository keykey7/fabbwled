package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section633 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(633);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        Your unerring sense of direction, even at sea, serves you well. It
                        "is not long before you find a familiar stretch of coast.
                        "Roll one die.
                        """)
                .choice(a -> a.text("Score 1 or 2 turn to 120"), b -> b.clickableTurnTo(120))
                .choice(a -> a.text("Score 3 or 4 turn to 430"), b -> b.clickableTurnTo(430))
                .choice(a -> a.text("Score 5 or 6 turn to 136"), b -> b.clickableTurnTo(136));
    }
}
