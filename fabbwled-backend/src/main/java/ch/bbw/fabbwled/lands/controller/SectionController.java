package ch.bbw.fabbwled.lands.controller;

import ch.bbw.fabbwled.lands.book.SectionDto;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class SectionController {

	private final SectionService sectionService;

	/**
	 * Peek at a book section without actually going there.
	 */
	@GetMapping("/api/section/{bookId}/{sectionId}")
	public SectionDto byId(@PathVariable int bookId, @PathVariable int sectionId) {
		return sectionService.byId(new SectionId(bookId, sectionId));
	}

	@PostMapping("/api/section/click")
	public SectionDto click(@RequestBody SectionClick click) {
		return sectionService.onClick(click.clickId());
	}

	public record SectionClick(int clickId) {
	}
}
