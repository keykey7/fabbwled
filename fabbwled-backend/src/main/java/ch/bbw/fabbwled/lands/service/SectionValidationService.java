package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.book.FabledSection;
import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SectionValidationService {

	final Set<FabledSection> allSections;

	@EventListener(ContextRefreshedEvent.class)
	void validateUniqueId() {
		var ids = allSections.stream().map(FabledSection::getId).toList();
		var duplicates = ids.stream().filter(i -> Collections.frequency(ids, i) > 1)
				.collect(Collectors.toSet());
		if (!duplicates.isEmpty()) {
			throw new FabledTechnicalException("duplicate section id's exist: " + duplicates);
		}
	}
}
