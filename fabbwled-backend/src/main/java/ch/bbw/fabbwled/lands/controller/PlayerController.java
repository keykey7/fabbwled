package ch.bbw.fabbwled.lands.controller;

import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class PlayerController {

	private final PlayerSession playerSession;

	/**
	 * @return the current player session
	 */
	@GetMapping("/api/player")
	public PlayerSession.PlayerDto whoami() {
		return playerSession.getPlayer();
	}
}
