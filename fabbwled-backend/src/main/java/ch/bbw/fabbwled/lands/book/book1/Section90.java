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
public class Section90 implements SectionHandler {
    @Override
    public SectionId getId() {
        return new SectionId(1, 90);
    }


    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        Your ship is limping through unknown waters. The sun beats
                        down, and the wind drops to a whisper.
                        ‘What direction, Cap’n?’ asks the bosun.""")
                .clickableDifficultyRollWithOptions(current, AbilityEnum.SCOUTING, 10,
                        success -> success.clickableTurnTo(633),
                        failure -> failure.clickableTurnTo(484));
    }
}
