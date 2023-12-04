package ch.bbw.fabbwled.lands.controller;

import java.util.List;

import ch.bbw.fabbwled.lands.book.SectionDto;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.marketplace.MarketPlace;
import ch.bbw.fabbwled.lands.service.MarketPlaceService;
import ch.bbw.fabbwled.lands.service.SectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController("/api")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class SectionController {

	private final SectionService sectionService;
	private final MarketPlaceService marketPlaceService;

	/**
	 * Peek at a book section without actually going there.
	 */
	@GetMapping("/section/{bookId}/{sectionId}")
	public SectionDto byId(@PathVariable int bookId, @PathVariable int sectionId) {
		return sectionService.byId(new SectionId(bookId, sectionId));
	}

	@PostMapping("/section/click")
	public SectionDto click(@RequestBody SectionClick click) {
		return sectionService.onClick(click.clickId());
	}

    @GetMapping("/display-marketplace/")
    public List<MarketPlace> displayMarketplace(
        @RequestBody @Valid List<MarketPlace> marketPlaceValues
    ) {
        return this.marketPlaceService.displayMarketplace(marketPlaceValues);
    }

	public record SectionClick(int clickId) {
	}
}
