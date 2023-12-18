package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section515 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(515);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .clickableDifficultyRollWithOptions(current, AbilityEnum.MAGIC, 11,
                        success -> success.clickableTurnTo(205),
                        failure -> failure.clickableTurnTo(314));
    }
}
