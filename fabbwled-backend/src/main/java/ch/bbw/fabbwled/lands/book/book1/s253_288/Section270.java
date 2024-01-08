package ch.bbw.fabbwled.lands.book.book1.s253_288;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Section270 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 270);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        You make a swift inspection of the casements, walls and
                        furnishings, searching for secret panels where treasure might be
                        concealed.
                        """)
                .clickableDifficultyRollWithOptions(current, AbilityEnum.THIEVERY, 12,
                        success -> success.clickableTurnTo(59),
                        failure -> failure.clickableTurnTo(243));
    }
}
