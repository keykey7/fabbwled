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
public class Section22 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 22);
    }



    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        You reach down and deftly pull out the ceramic plug. A gush of
                        foul-smelling emerald green liquid spills on to the floor, and the
                        golem twitches once before collapsing. The other golem is
                        coming to life, however. You’ll have to be quick to get it in
                        time!""")
                .clickableDifficultyRollWithOptions(current, AbilityEnum.COMBAT, 9,
                        success -> success.clickableTurnTo(539),
                        failure -> failure.clickableTurnTo(647));
    }


}
