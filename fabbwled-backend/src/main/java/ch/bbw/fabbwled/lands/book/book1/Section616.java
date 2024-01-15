package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section616 implements SectionHandler {

    @Override
    public SectionId getId() {
        return SectionId.book1(616);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode
                .root().text("""
                        You swim up to your ship, and haul yourself on board. The
                        crew is astonished to see you.
                        ‘Another one of the sea devils,’ says the first mate.
                        ‘We don’t want any more bad luck,’ says a crew member.
                        ‘Aye, we’ll have to kill this one as well,’ says the first mate.
                        You try to protest, but your words come out as a bubbling,
                        fishy warble. The first mate finishes you with his spear. Your
                        adventuring days are over unless you have a resurrection deal. If
                        you do have a deal, when you come back to life, note that you
                        have lost your ship and crew – as far as they are concerned, you
                        never came back from the depths.
                        """)
                .clickableTurnTo(1);
    }
}
