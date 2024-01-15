package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section628 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(628);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                After a hard climb, you are half-way up the side of a mountain
                when you discover a thin, precarious path, leading up. The sun
                beats down, and you are sweating heavily. You take a swig of
                water from your canteen and proceed up the path.
                After a while you have to stop to take more water. To your
                horror, your water supply has turned sour and undrinkable!
                """)
                .clickableDifficultyRollWithOptions(current, AbilityEnum.MAGIC, 10,
                        success -> success.clickableTurnTo(374),
                        failure -> failure.clickableTurnTo(643));
    }
}
