package ch.bbw.fabbwled.lands.book.rules;

import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;
import ch.bbw.fabbwled.lands.service.effects.OnPlayerChange;
import org.springframework.stereotype.Component;

@Component
public class StaminaNeverOverMaxRule implements OnPlayerChange.SimpleGameRule {

    @Override
    public void validate(PlayerDto before, PlayerDto after) {
        if (after.stamina() > after.staminaWhenUnwounded()) {
            throw new FabledTechnicalException("stamina cannot exceed maxStamina");
        }
    }
}
