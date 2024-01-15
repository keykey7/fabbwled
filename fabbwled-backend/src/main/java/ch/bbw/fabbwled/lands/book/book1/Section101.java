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
public class Section101 implements SectionHandler {
    @Override
    public SectionId getId() {
        return new SectionId(1, 101);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        You yell the name of their god again and again. The cultists clap
                        their hands over their ears, hopping about in horror.
                        ‘Aargh!’ howls the leader. ‘Stop your blaspheming, you
                        heathen devil!’
                        In their confusion, you make it to your equipment. Now
                        you can fight your way out.""")
                .clickableDifficultyRollWithOptions(current, AbilityEnum.COMBAT, 13,
                        success -> success.clickable(p -> p.setStamina(p.stamina() - 4), z -> success.clickableTurnTo(10)),
                        failure -> failure.clickableTurnTo(204));
    }
}
