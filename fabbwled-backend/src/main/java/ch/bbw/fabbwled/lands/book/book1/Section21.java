package ch.bbw.fabbwled.lands.book.book1;


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
public class Section21 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 21);
    }


    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        While making your way through the back streets of the poor
                        quarter you are set upon a knife-wielding thug, who is intent of
                        relieving you of your purse.
                        If you don’t want to fight him, you can try a CHARISMA roll
                        at a Difficulty of 8 to try to talk your way out of this unpleasant
                        situation.""")
                .clickableDifficultyRollWithOptions(current, AbilityEnum.CHARISMA, 8,
                        success -> success.clickableTurnTo(10),
                        failure -> {
                            if (current.baseStats().combat() > 4 && current.defence() > 7 && current.stamina() > 6) {
                                failure.clickable(p -> p.addShards(15), z -> failure.clickableTurnTo(10));
                            } else {
                                failure.clickable(p -> p.withStamina(1).addShards(Math.max(-50, -p.shards())), y -> failure.clickableTurnTo(10));
                            }


                        });
    }


}
