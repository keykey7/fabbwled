package ch.bbw.fabbwled.lands.book.book1.s325_360;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;

public class Section340 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 340);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .clickableDifficultyRollWithOptions(current, AbilityEnum.SCOUTING, 10,
                        success -> success.clickableTurnTo(239),
                        failure -> failure.clickableTurnTo(34))
                .clickableDifficultyRollWithOptions(current, AbilityEnum.THIEVERY, 10,
                        success -> success.clickableTurnTo(239),
                        failure -> failure.clickableTurnTo(34));
    }
}
