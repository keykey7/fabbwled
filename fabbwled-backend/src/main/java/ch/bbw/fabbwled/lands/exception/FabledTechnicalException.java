package ch.bbw.fabbwled.lands.exception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Unexpected behaviour the player should never see during normal play.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
@StandardException
public class FabledTechnicalException extends RuntimeException {
}
