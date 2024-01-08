package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section509 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(509);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        You spot a pressure pad at the base of the idol just in time –
                        who knows what unpleasant trap that would have set off!
                        Quickly, you remove the chain mail and sling it over your
                        shoulder. Note the gold chain mail of Tyrnai on your
                        Adventure Sheet – as it is gold, it is useless as actual armour.
                        The eyes of the jaguar headed idol seem to turn to look at
                        you.""")
                .clickableDifficultyRollWithOptions(current, AbilityEnum.SANCTITY, 10,
                        success -> success.clickableTurnTo(625),
                        failure -> failure.clickableTurnTo(279));
    }
}
