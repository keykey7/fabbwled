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
public class Section79 implements SectionHandler {
    @Override
    public SectionId getId() {
        return new SectionId(1, 79);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        As you try to lose them
                        in the tunnels. It is hard because they know the layout better
                        than you do.""")
                .clickableDifficultyRollWithOptions(current, AbilityEnum.SCOUTING, 14,
                        success -> success.clickableTurnTo(224),
                        failure -> failure.clickableTurnTo(381));
    }
}
