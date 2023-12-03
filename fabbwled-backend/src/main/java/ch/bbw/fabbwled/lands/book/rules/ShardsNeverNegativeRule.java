package ch.bbw.fabbwled.lands.book.rules;

import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.effects.OnPlayerChange;
import org.springframework.stereotype.Component;

@Component
public class ShardsNeverNegativeRule implements OnPlayerChange {

    @Override
    public void validate(PlayerDto before, PlayerDto after) throws RuleViolationException {
        if (after.shards() < 0) {
            throw new RuleViolationException("shards cannot become negative");
        }
    }
}
