package ch.bbw.fabbwled.lands.book.book1;


import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Section5 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 5);
    }



    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("It is a tough climb upwards but not impossible.")
                .activeIf(current.possessions().contains("clibmbing gear"), c -> c.clickableTurnTo(652))
                .clickableDifficultyRollWithOptions(current, AbilityEnum.SCOUTING, 10,
                        success -> success.clickableTurnTo(652),
                        failure -> failure.clickableTurnTo(529));
    }
}
