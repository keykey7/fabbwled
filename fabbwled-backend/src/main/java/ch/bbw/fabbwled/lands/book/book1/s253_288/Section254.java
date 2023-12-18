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
public class Section254 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 254);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        You ask for a kiss. ‘Oh no!’ cry the mer-folk, ‘A kiss is not so
                        easily won! Tell us a tale to stir our hearts – then we may reward
                        you.’
                        """)
                .clickableDifficultyRollWithOptions(current, AbilityEnum.CHARISMA, 9,
                        success -> success.clickableTurnTo(12),
                        failure -> failure.clickableTurnTo(281));
    }
}
