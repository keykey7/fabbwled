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
public class Section60 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 60);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        You are crossing the wild country of north-east Sokara.
                        You come across a wolf, fight it!
                        """)
                .clickableDifficultyRollWithOptions(current, AbilityEnum.COMBAT, 8,
                        success -> success.clickableTurnTo(518),
                        failure -> {
                            if (current.baseStats().combat() > 3 && current.defence() > 5 && current.stamina() > 7) {
                                failure.clickableTurnTo(458);
                            } else {
                                failure.clickableTurnTo(201);
                            }
                        });
    }
}
