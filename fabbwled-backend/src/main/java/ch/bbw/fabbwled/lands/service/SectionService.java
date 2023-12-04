package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.book.SectionDto;
import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.yaml.YamlSectionHandler;
import ch.bbw.fabbwled.lands.book.yaml.YamlSectionLoader;
import ch.bbw.fabbwled.lands.exception.FabledBusinessException;
import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class SectionService {

	final PlayerSession playerSession;

	final Set<SectionHandler> allSections;

	public SectionService(PlayerSession playerSession, Set<SectionHandler> allSections) {
		this.playerSession = playerSession;
		this.allSections = allSections;

		// Verify sections.
		var loader = new YamlSectionLoader();
		loader.loadSections(loader.yamlFiles())
                .stream().map(yaml -> new YamlSectionHandler(playerSession, yaml))
                .forEach(allSections::add);
	}

	public SectionHandler getSectionHandler(@NonNull SectionId id) {
		return allSections.stream()
				.filter(x -> id.equals(x.getId()))
				.findAny()
				.orElseThrow(() -> new FabledTechnicalException("unknown section " + id));
	}

	public SectionDto byId(@NonNull SectionId id) {
		var handler = getSectionHandler(id);
        var player = playerSession.getPlayer();
        var ticks = new SectionDto.SectionTicks(handler.getMaxTicks(), player.getTicks());
		var sectionDto = new SectionDto(handler.getId(), ticks, handler.getBody(player));
		if (log.isDebugEnabled()) { // because it's expensive
			log.debug("returning {}: {}", sectionDto.id(), sectionDto.body().asPlainText());
			log.debug("available clickIds: {}", sectionDto.body().allActiveClickIds());
		}
		return sectionDto;
	}

	public SectionDto onClick(int clickId) {
		var activeSectionId = playerSession.getPlayer().currentSection();
        log.info("handling clickId={} on {}", clickId, activeSectionId);
        // we render the current section to obtain all clickable actions
        var section = byId(activeSectionId);
        playerSession.update(oldPlayer -> section.body().allActiveClickIds()
                .stream()
                .filter(x -> x.clickId() == clickId)
                .findAny()
                .orElseThrow(() -> new FabledBusinessException("ClickId " + clickId + " is unavailable or disabled."))
                .playerChange()
                .apply(oldPlayer));
		// we have to re-render as we changed state
		return byId(playerSession.getPlayer().currentSection());
	}
}
