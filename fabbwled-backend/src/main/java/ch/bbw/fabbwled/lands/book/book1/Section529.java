package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section529 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(529);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        You loose your footing, and fall. Fortunately, some bushes break
                        your fall, and you lose only 4 Stamina points.
                        """)
                .clickable(a -> a.withStamina(current.stamina() - 4), b -> b.text("Loose 4 Stamina"))
                .activeIf(current.doIStillLive(), a -> a
                        .text("You still live!")
                        .clickableTurnTo(474));
    }
}

