package ch.bbw.fabbwled.lands.book.rules;

import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.effects.OnAttributeRefresh;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * [p.12] Your Defence score is equal to:
 * your COMBAT score
 * plus your Rank
 * plus the bonus for the armour you're wearing (if any). -> done by later effects
 */
@Order(OnAttributeRefresh.AFTER_BASE_STATS) // effect to be applied AFTER combat effects and BEFORE defence item effects
@Component
public class DefenceBaseScore implements OnAttributeRefresh {

    @Override
    public PlayerDto applyEffect(PlayerDto basePlayer) {
        // we override everything. there is no "base" defence. It's always computed
        return basePlayer.withDefence(basePlayer.rank().getRankNumber() + basePlayer.baseStats().combat());
    }
}
