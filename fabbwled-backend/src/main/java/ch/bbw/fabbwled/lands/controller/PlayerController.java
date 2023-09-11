package ch.bbw.fabbwled.lands.controller;

import ch.bbw.fabbwled.lands.service.PlayerSession;
import ch.bbw.fabbwled.lands.character.Character;
import ch.bbw.fabbwled.lands.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.function.UnaryOperator;

@Validated
@RestController
@RequiredArgsConstructor
public class PlayerController {

	private final PlayerSession playerSession;
	private final CharacterService characterService;

	/**
	 * @return the current player session
	 */
	@GetMapping("/api/player")
	public PlayerSession.PlayerDto whoami() {
		return playerSession.getPlayer();
	}

	@GetMapping("/api/player/characters/{bookId}/all")
	public List<Character.CharacterDto> getAllCharacters(@RequestParam bookId) {
		return characterService.getAllCharacters(bookId);
	}

	@GetMapping("/api/player/getCharacter")
	public PlayerSession.PlayerDto getCharacter() {
		return playerSession.getPlayer().character();
	}

	@PostMapping("/api/player/setCharacter")
	public PlayerSession.PlayerDto setCharacter(@RequestBody Character.CharacterDto character) {
		// TODO Enforce rules for character creation (how many posessions, rank ...)
		playerSession.update( player -> {
			player.character(character);
			return player;
	    });
		return playerSession.getPlayer();
	}


}
