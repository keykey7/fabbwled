package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section615 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(615);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        Lacuna is the Goddess of the Moon, and of the Wilderness. She aids hunters, and woodsmen, and
                        travellers in the lost places of the world. Her temple here is a long hall of oak, covered in
                        vines and plants of all kinds. Inside, flowers fill the air with a pure and clean scent.
                        """)
                .choice(a -> a.text("Become an initiate"), b -> b.clickableTurnTo(170))
                .choice(a -> a.text("Renounce worship"), b -> b.clickableTurnTo(253))
                .choice(a -> a.text("Seek a blessing"), b -> b.clickableTurnTo(482))
                .choice(a -> a.text("Leave the temple"), b -> b.clickableTurnTo(400));
    }
}
