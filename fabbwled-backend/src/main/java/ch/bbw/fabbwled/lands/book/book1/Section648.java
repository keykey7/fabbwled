package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section648 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(648);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        You remain quiet as a mouse, behind a pile of coins. After a
                        long wait, the sea dragon slithers into the water, and swims out
                        on some errand.
                        You have some time to loot the hoard. You scrabble about
                        for the chest that Oliphard the Wizardly wanted you to find for
                        him. You find a rune-covered box which is positively glowing
                        with magic.
                        You hear the sea dragon returning. Quickly you climb up through the hole in the
                        roof on to an island in the middle of the lake. From there, you
                        manage to get a lift on a passing boat, and make it safely to
                        Cadmium village.
                        During the journey, you examine the magic chest , but it is
                        guarded by great sorcery and is impossible to open.
                        """)
                .clickable(playerDto -> playerDto.addPossession("Magic chest"), a -> a.text(" Note the magic chest on your Adventure Sheet."))
                .clickableTurnTo(135);
    }
}
