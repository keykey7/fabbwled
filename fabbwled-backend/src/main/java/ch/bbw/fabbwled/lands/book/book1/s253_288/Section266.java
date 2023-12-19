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
public class Section266 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 266);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        ‘You rebel swine,’ slurs one of them.
                        ‘Not showing proper respect to the army,’ says another.
                        ‘That can’t go unpunished!’
                        They wade into you, intent on beating you into submission
                        """)
                .clickableDifficultyRollWithOptions(current, AbilityEnum.COMBAT, 12,
                        success -> success.clickableTurnTo(76),
                        failure -> failure.clickableTurnTo(498));
    }
}
