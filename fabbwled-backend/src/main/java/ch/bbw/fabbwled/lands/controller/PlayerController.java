package ch.bbw.fabbwled.lands.controller;

import ch.bbw.fabbwled.lands.book.FabledSection;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import ch.bbw.fabbwled.lands.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class PlayerController {

	private final PlayerSession playerSession;

	private final SectionService sectionService;

	/**
	 * @return the current player session
	 */
	@GetMapping("/api/player")
	public PlayerDto whoami() {
		return PlayerDto.from(playerSession);
	}

	/**
	 * Move to a new book section (if allowed).
	 */
	@PostMapping("/api/player/section")
	public FabledSection moveTo(@RequestBody SectionId newSection) {
		return sectionService.moveTo(newSection);
	}

	public record PlayerDto(String name, SectionId currentSection) {
		public static PlayerDto from(PlayerSession playerSession) {
			// we should start using MapStruct...
			return new PlayerDto(playerSession.getName(), playerSession.getCurrentSection());
		}
	}
}
