package ch.bbw.fabbwled.lands.service.effects;

import ch.bbw.fabbwled.lands.character.PlayerDto;

/**
 * Game-rules that apply whenever an attribute of the player changes.
 */
public interface OnPlayerChange {

    PlayerDto applyEffect(PlayerDto before, PlayerDto after);

    interface SimpleGameRule extends OnPlayerChange {

        default PlayerDto applyEffect(PlayerDto before, PlayerDto after) {
            validate(before, after);
            return after;
        }

        void validate(PlayerDto before, PlayerDto after);
    }
}
