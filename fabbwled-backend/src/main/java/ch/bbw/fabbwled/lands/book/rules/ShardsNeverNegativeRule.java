package ch.bbw.fabbwled.lands.book.rules;

import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;
import ch.bbw.fabbwled.lands.service.effects.OnPlayerChange;
import org.springframework.stereotype.Component;

@Component
public class ShardsNeverNegativeRule implements OnPlayerChange.SimpleGameRule {

    @Override
    public void validate(PlayerDto before, PlayerDto after) {
        if (after.shards() < 0) {
            throw new FabledTechnicalException("shards cannot become negative");
        }
    }
}
