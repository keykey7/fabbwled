package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section511 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(511);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        Your arcane knowledge tells you much about the undead.
                        Ghouls are known to eat the flesh of the dead as well as the
                        living. They like to make their homes in crypts and graveyards
                        and they never venture out during the day, as sunlight burns
                        their pallid undead flesh. Also, they cannot abide a powder of
                        salt and iron filings mixed together. You can purchase these
                        ingredients for 15 Shards at many a market stall.
                        If you make the purchase, note you have salt and iron
                        filings on your Adventure Sheet.
                        """)
                .clickableDifficultyRollWithOptions(current, AbilityEnum.SCOUTING, 9,
                        success -> success.clickableTurnTo(419),
                        failure -> failure.clickableTurnTo(360));
    }
}
