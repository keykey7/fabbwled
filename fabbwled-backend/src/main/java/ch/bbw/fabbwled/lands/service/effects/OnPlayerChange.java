package ch.bbw.fabbwled.lands.service.effects;

import ch.bbw.fabbwled.lands.exception.FabledBusinessException;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import lombok.experimental.StandardException;

/**
 * Game-rules that apply whenever an attribute of the player changes.
 */
public interface OnPlayerChange {

    void validate(PlayerDto before, PlayerDto after) throws RuleViolationException;

    @StandardException
    class RuleViolationException extends FabledBusinessException {

    }
}
