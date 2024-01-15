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
public class Section39 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 39);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        You and some of your crew clamber aboard the wreck. You
                        find some dead sailors amid the wreckage. Their bodies are
                        curiously bloated. Make a SCOUTING roll at Difficulty 9.""")
                .clickableDifficultyRollWithOptions(current, AbilityEnum.SCOUTING, 9,
                        success -> success.clickableTurnTo(194),
                        failure -> failure.clickableTurnTo(456));
    }
}
