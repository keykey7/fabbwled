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
public class Section94 implements SectionHandler {
    @Override
    public SectionId getId() {
        return new SectionId(1, 94);
    }


    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        At last, the golem is defeated. You manage to get into the
                        temple without being noticed by anyone else. Inside, it is cool
                        and dark, filled with an unearthly stillness. You reach forward to
                        strip the armour off the idol of Tyrnai.""")
                .clickableDifficultyRollWithOptions(current, AbilityEnum.THIEVERY, 12,
                        success -> success.clickableTurnTo(509),
                        failure -> failure.clickableTurnTo(228));
    }
}
