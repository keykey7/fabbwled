package ch.bbw.fabbwled.lands.book.rules;

import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.effects.OnPlayerChange;
import org.springframework.stereotype.Component;

@Component
public class StaminaNeverOverMaxRule implements OnPlayerChange {

    @Override
    public void validate(PlayerDto before, PlayerDto after) throws RuleViolationException {
        if (after.stamina() > after.staminaWhenUnwounded()) {
            throw new RuleViolationException("stamina cannot exceed maxStamina");
        }
    }
}
