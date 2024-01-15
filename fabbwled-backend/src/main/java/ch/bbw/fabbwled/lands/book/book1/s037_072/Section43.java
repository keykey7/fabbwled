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
public class Section43 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 43);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        You must fight. Luckily, your magic weapon will be effective.
                        """)
                .clickableDifficultyRollWithOptions(current, AbilityEnum.COMBAT, 5,
                        success -> success.clickableTurnTo(490),
                        failure -> {
                            if (current.resurrectionArrangement() != null) {
                                failure.clickableTurnTo(5);
                            } else {
                                failure.clickableTurnTo(1);
                            }
                        });
    }
}
