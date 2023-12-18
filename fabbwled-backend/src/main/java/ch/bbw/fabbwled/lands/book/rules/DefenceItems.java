package ch.bbw.fabbwled.lands.book.rules;

import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.effects.OnAttributeRefresh;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Order(OnAttributeRefresh.AFTER_BASE_DEFENCE) // AFTER base defence calculation
@Component
public class DefenceItems implements OnAttributeRefresh  {

    @Override
    public PlayerDto applyEffect(PlayerDto basePlayer) {
        return basePlayer.withDefence(basePlayer.defence() + getMaxDefenceBonus(basePlayer));
    }

    private int getMaxDefenceBonus(PlayerDto basePlayer) {
        // TODO 03-Dec-2023/kk: this is very hacky since we extract defence attribute from the string...
        var regex = ".+\\(DEFENCE \\+(\\d)\\)";
        return basePlayer.possessions().stream()
                .map(x -> x.toUpperCase(Locale.ROOT))
                .filter(x -> x.matches(regex))
                .mapToInt(x -> Integer.parseInt(x.replaceAll(regex, "$1")))
                .max()
                .orElse(0);
    }
}
