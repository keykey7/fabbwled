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
public class Section24 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 24);
    }



    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        You will need to subdue the king and his henchmen with a
                        spell.""")
                .clickableDifficultyRollWithOptions(current, AbilityEnum.MAGIC, 12,
                        success -> success.clickableTurnTo(644),
                        failure -> failure.clickableTurnTo(208));
    }


}
