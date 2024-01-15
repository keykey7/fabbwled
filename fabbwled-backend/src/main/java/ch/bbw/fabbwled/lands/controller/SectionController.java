package ch.bbw.fabbwled.lands.controller;

import ch.bbw.fabbwled.lands.book.SectionDto;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
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

    @PostMapping("/api/section/fight")
    public SectionDto fight(@PathVariable int sectionId) {
        return null;
    }

	public record SectionClick(int clickId) {
	}
}
