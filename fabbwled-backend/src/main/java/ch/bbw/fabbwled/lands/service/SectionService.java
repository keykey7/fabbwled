package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.book.SectionDto;
import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.yaml.YamlSectionHandler;
import ch.bbw.fabbwled.lands.book.yaml.YamlSectionLoader;
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
		var sectionDto = new SectionDto(handler.getId(), handler.getTicks(), handler.getBody());
		if (log.isDebugEnabled()) { // because it's expensive
			log.debug("returning {}: {}", sectionDto.id(), sectionDto.body().asPlainText());
			log.debug("available clickIds: {}", sectionDto.body().allClickIds());
		}
		return sectionDto;
	}

	public SectionDto onClick(int clickId) {
		var activeSectionId = playerSession.getPlayer().currentSection();
		var handler = getSectionHandler(activeSectionId);
		log.info("handling clickId={} on {}", clickId, activeSectionId);
		handler.onClick(clickId);
		// we have to re-render as we changed state
		return byId(playerSession.getPlayer().currentSection());
	}
}
