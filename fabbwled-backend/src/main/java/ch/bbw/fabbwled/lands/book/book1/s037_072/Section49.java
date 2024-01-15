package ch.bbw.fabbwled.lands.book.book1.s037_072;


import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class Section49 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 49);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        Not taking any chances, you charge the soldier, yelling a warcry.
                        He starts back in astonishment. Just then, several archers pop up
                        from behind the rocks above, and let loose a volley of arrows.
                        One takes you in the leg.
                        You fall to one knee, and the soldier melts away into the rocks.
                        Alone, and wounded in the leg, you cannot climb upwards. You have
                        to go back down though the descent will be difficult with a dodgy 
                        leg.""")
                .clickableDifficultyRollWithOptions(current, AbilityEnum.CHARISMA, 8,
                        success -> success.clickableTurnTo(529),
                        failure -> failure.clickableTurnTo(474)
                );
    }
}
