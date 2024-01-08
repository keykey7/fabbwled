package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section632 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(632);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        Make a SANCTITY roll at Difficulty 12.
                        """)
                .clickableDifficultyRollWithOptions(current, AbilityEnum.SANCTITY, 12,
                        success -> success.clickableTurnTo(392),
                        failure -> failure.clickableTurnTo(125));
    }
}
