package ch.bbw.fabbwled.lands.book.book1.s037_072;


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
public class Section55 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 55);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        You remember that this is a trap set up by the cannibal cultists of
                        Badogor. While one pretends to be hurt, two other skulk in the
                        shadows, waiting to ambush the curious. Forewarned you are
                        able to take them by surprise. You cut one down in seconds and
                        the figure on the ground runs off with a shriek of terror. The
                        third cultist, however, turns to fight you.""")
                .clickableDifficultyRollWithOptions(current, AbilityEnum.COMBAT, 8,
                        success -> success.clickableTurnTo(10),
                        failure -> {
                            if (current.baseStats().combat() > 3 && current.defence() > 5 && current.stamina() > 7) {
                                failure.clickableTurnTo(10);
                            } else {
                                failure.clickableTurnTo(1);
                            }
                        });
    }
}
