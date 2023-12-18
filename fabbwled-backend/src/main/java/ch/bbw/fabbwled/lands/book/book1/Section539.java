package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section539 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(539);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        You roll across the eaves of the gate and reach down to pull the
                        plug on the other golem in one deft movement. It ceases to
                        function before it can give the alarm. It is an easy matter to get
                        into the temple from the roof.
                        Inside, it is cool and dark, filled with an unearthly stillness.
                        You reach forward to strip the armour off the idol of Tyrnai.
                        """)
                .clickableDifficultyRollWithOptions(current, AbilityEnum.THIEVERY, 12,
                        success -> success.clickableTurnTo(509),
                        failure -> failure.clickableTurnTo(228));
    }
}
