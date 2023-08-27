package ch.bbw.fabbwled.lands.exception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Most likely a rule violation or invalid behaviour by the player.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@StandardException
public class FabledBusinessException extends RuntimeException {
}
