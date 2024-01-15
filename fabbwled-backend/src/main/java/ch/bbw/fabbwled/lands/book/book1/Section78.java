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
public class Section78 implements SectionHandler {
    @Override
    public SectionId getId() {
        return new SectionId(1, 78);
    }


    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .clickableDifficultyRollWithOptions(current, AbilityEnum.SCOUTING, 10,
                        success -> success.clickableTurnTo(524),
                        failure -> failure.clickableTurnTo(415));
    }
}
