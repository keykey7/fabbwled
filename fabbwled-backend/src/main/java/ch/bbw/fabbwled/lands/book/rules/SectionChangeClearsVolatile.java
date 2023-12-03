package ch.bbw.fabbwled.lands.book.rules;

import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.effects.OnPlayerChange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(0) // early
@Component
public class SectionChangeClearsVolatile implements OnPlayerChange {
    @Override
    public PlayerDto applyEffect(PlayerDto before, PlayerDto after) {
        if (!before.currentSection().equals(after.currentSection())) {
            log.info("changing section effect");
            after = after.withDiceRoll(0) // clear last dice roll
                    .withLastDifficultyRoll(null)
                    .withVolatileSectionStore(null);
        }
        return after;
    }
}
