package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section528 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(528);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("You attempt to sneak in through the back of the temple that night. ")
                .clickableDifficultyRollWithOptions(current, AbilityEnum.THIEVERY, 11,
                        success -> success.clickableTurnTo(445),
                        failure -> failure.clickableTurnTo(284));
    }
}
