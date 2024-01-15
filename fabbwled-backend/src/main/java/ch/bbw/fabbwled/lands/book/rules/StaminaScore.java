package ch.bbw.fabbwled.lands.book.rules;

import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.effects.OnAttributeRefresh;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(OnAttributeRefresh.AFTER_BASE_STATS) // effect to be applied AFTER combat effects and BEFORE defence item effects
@Component
public class StaminaScore implements OnAttributeRefresh{

    @Override
    public PlayerDto applyEffect(PlayerDto basePlayer) {
        return null;
    }
}
