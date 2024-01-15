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
public class Section13 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 13);
    }



    @Override
    public SectionNode getBody(PlayerDto current) {

        return SectionNode.root().text("""
                ‘The Violet Ocean’s a dangerous place, Cap’n’, says the first
                mate. ‘The crew probably won’t follow you there if they don’t
                think you’re good enough!’""")
                .activeIf(current.rank().getRankNumber() >=4, x -> x.clickableTurnTo(55))
                .activeIf(current.rank().getRankNumber() < 4, x -> x.choice(
                        y -> y.text("The first mate advises you against the ocean journey.").clickableTurnTo(507),
                        t -> t.text("Insist on making the trip").clickableDifficultyRollWithOptions(current, AbilityEnum.CHARISMA,12,
                                success -> success.clickableTurnTo(55),
                                failure -> failure.clickableTurnTo(507))));

    }
}
