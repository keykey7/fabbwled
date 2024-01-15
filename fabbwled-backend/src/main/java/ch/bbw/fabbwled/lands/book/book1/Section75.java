package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Section75 implements SectionHandler {
    @Override
    public SectionId getId() {
        return new SectionId(1, 75);
    }

    // not finished yet, can only turn to, because of missing time
    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        The high priest takes you to a private chamber.
                        ‘You may be just what the temple needs,’ he says, ‘a good,
                        old-fashioned thief! There is a suit of armour made entirely of
                        gold – ceremonial only of course. Nevertheless, we would like
                        to, er, have it donated to us.’
                        ‘I see,’ you reply, ‘and where is the armour?’
                        ‘Well that’s the tricky part – it’s in the Temple of Tyrnai, in
                        Caran Baru. In fact, it’s worn by the idol of Tyrnai himself in
                        the temple! Can you bring us the gold chain mail of Tyrnai? In
                        return, we will instruct you in the roguish arts.’
                        If you want to take up the mission for the Temple of Sig,
                        record the codeword Armour.""")
                .clickableTurnTo(235);
    }
}
