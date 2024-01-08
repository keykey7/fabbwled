package ch.bbw.fabbwled.lands.book.book1.s325_360;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;

public class Section328 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 328);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                            You are not sure where to start looking for the ghoul, so you
                            wander around, looking for stories, strange murders – anything
                            that might put you on its trail.
                        """)
                .clickableDifficultyRollWithOptions(current, AbilityEnum.SCOUTING, 12,
                        success -> success.clickableTurnTo(419),
                        failure -> failure.clickableTurnTo(360));
    }
}
