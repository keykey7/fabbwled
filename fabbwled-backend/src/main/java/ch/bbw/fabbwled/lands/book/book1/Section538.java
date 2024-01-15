package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section538 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(538);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        ‘Refusing to pay your taxes, eh!’ says their leader. That’s a crime for sure!’
                        They close in around you. You’ll have to think fast
                        """)
                .clickableDifficultyRollWithOptions(current, AbilityEnum.CHARISMA, 11,
                        success -> success.clickableTurnTo(18),
                        failure -> failure.clickableTurnTo(157));
    }
}
