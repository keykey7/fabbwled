package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.exception.FabledBusinessException;
import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;
import ch.bbw.fabbwled.lands.book.FabledSection;
import ch.bbw.fabbwled.lands.book.SectionId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class SectionService {

	final PlayerSession playerSession;

	final Set<FabledSection> allSections;

	public FabledSection byId(@NonNull SectionId id) {
		return allSections.stream()
				.filter(x -> id.equals(x.getId()))
				.findAny()
				.orElseThrow(() -> new FabledTechnicalException("unknown section " + id));
	}

	public boolean isValidTarget(@NonNull SectionId newId) {
		return byId(playerSession.getCurrentSection()).getGoto().stream().anyMatch(x -> newId.equals(x.target()));
	}

	public FabledSection moveTo(@NonNull SectionId nextId) {
		if (!isValidTarget(nextId)) {
			throw new FabledBusinessException("cannot move to section " + nextId);
		}
		log.info("transitioning player from {} to {}", playerSession.getCurrentSection(), nextId);
		playerSession.setCurrentSection(nextId);
		return byId(nextId);
	}
}
