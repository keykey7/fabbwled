package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.BlessingEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Section613 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(613);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        Heavy black clouds race towards you across the sky,
                        whipping the waves into a frenzy. The crew mutter amongst themselves fearfully.
                        Otherwise the storm hits with full fury.
                        Roll one die if you ship is a barque, two dice if it is abrigantine, or three dice if a galleon.
                        Add 1 to the roll if you have a good crew; add 2 if the crew is excellent.
                        Score 1-3 Ship sinks turn to 485
                        Score 4-5 The mast splits turn to 70
                        Score 6-20 You weather the storm turn to 439
                        """)
                .activeIf(current.blessings().contains(BlessingEnum.ALVIR_AND_VALMIR), a -> a
                        .text("If you have the blessings from Alvir and Valmir and therefore are not affected by the storm.")
                        .clickable(playerDto -> playerDto.withBlessings(current.blessings().stream().filter(b -> !b.equals(BlessingEnum.ALVIR_AND_VALMIR)).collect(Collectors.toSet())), x -> x.text("Remove blessing Alvir and Valmir"))
                        .clickableTurnTo(439))
                .choice(a -> a.text("Score 1-3"), b -> b.clickableTurnTo(485))
                .choice(a -> a.text("Score 4-6"), b -> b.clickableTurnTo(70))
                .choice(a -> a.text("Score 6-20"), b -> b.clickableTurnTo(439));
    }
}
