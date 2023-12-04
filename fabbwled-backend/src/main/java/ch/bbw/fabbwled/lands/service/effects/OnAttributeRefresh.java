package ch.bbw.fabbwled.lands.service.effects;

import ch.bbw.fabbwled.lands.character.PlayerDto;

/**
 * Any effect that can update/compute any attribute of {@code PlayerDto}.
 * This includes items like "Violin (CHARISMA +3)" as well as more complicated things like
 * "your curse makes you ugly: you loose one rank and until you find a cure".
 * The order of those effects is important. For example updates to COMBAT impact the DEFENCE score.
 */
public interface OnAttributeRefresh {

    int BASE_STATS = 0;
    int AFTER_BASE_STATS = 10;
    int AFTER_BASE_DEFENCE = 20;

    PlayerDto applyEffect(PlayerDto basePlayer);
}
