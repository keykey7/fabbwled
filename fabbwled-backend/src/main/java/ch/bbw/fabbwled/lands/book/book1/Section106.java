package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Section106 implements SectionHandler {
    @Override
    public SectionId getId() {
        return new SectionId(1, 106);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        Cross off the money. The young man smiles ingratiatingly, and
                        hands you the pearls.""")
                .clickableDifficultyRollWithOptions(current, AbilityEnum.MAGIC, 10,
                        success -> success.clickableTurnTo(306),
                        failure -> failure.clickableTurnTo(489));
    }
}
