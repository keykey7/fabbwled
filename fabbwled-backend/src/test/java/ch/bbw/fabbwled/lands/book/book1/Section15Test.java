package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.FabledTestBase;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.exception.FabledBusinessException;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import ch.bbw.fabbwled.lands.service.SectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class Section15Test extends FabledTestBase {
	@Autowired
	Section15 section15;

	@Autowired
	PlayerSession playerSession;

	@Autowired
	SectionService sectionService;

	@Test
	void canAvoidFight() {
		playerSession.setCurrentSection(section15.getId());
		var target = SectionId.book1(44);
		assertThat(sectionService.moveTo(target).getId()).isEqualTo(target);
	}

	@Test
	void cannotRestart() {
		playerSession.setCurrentSection(section15.getId());
		var target = SectionId.book1(1);
		assertThatThrownBy(() -> sectionService.moveTo(target)).isInstanceOf(FabledBusinessException.class);
	}
}
