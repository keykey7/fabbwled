package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section614 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(614);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        The  Stinking  River  has  cut  its  way  through  the  high  grounder.
                        On the edge of the chasm that overlooks the river lies the village  of  High  Therys.
                        Just outside of town, three bodies hang on a gallows, slowly rotting.
                        Inside, the villagers are having a fete. They welcome you. You can get some rest
                        and recuperation here. When you are ready, you leave.
                        """)
                .clickable(a -> a.addStamina(5), b -> b.text("Add up to 5 stamina"))
                .choice(a -> a.text("Follow the river north"), b -> b.clickableTurnTo(576))
                .choice(a -> a.text("Follow the river south"), b -> b.clickableTurnTo(82))
                .choice(a -> a.text("Head east into the country side"), b -> b.clickableTurnTo(278))
                .choice(a -> a.text("Go west to the main road"), b -> b.clickableTurnTo(558));
    }
}
