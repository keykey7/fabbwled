package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section536 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(536);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .activeIf(current.hasPossession("climbing gear"), a -> a
                        .text("If you have some climbing gear, ")
                        .clickableTurnTo(628)
                        .text("."))
                .activeElse(a -> a
                        .clickableDifficultyRollWithOptions(current, AbilityEnum.SCOUTING, 12,
                                success -> success.clickableTurnTo(562),
                                failure -> failure.clickableTurnTo(438)));
    }
}
