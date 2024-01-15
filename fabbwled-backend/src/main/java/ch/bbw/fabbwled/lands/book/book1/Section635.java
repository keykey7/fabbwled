package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

/* Tick-box does not exist here */
@Component
public class Section635 implements SectionHandler {

    @Override
    public SectionId getId() {
        return SectionId.book1(635);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        If the box above is empty, put a tick in it now and turn to 80 . If
                        it is already ticked, turn to 470 .
                        """)
                .clickableTurnTo(470);
    }
}
