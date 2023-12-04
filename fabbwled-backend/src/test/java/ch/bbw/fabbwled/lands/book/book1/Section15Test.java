package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.FabledTestBase;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.exception.FabledBusinessException;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import ch.bbw.fabbwled.lands.service.SectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class Section15Test extends FabledTestBase {
	@Autowired
	Section15 section15;

	@Autowired
	PlayerSession playerSession;

	@Autowired
	SectionService sectionService;

	@BeforeEach
	public void setToSection15() {
		playerSession.update(x -> x.withCurrentSection(section15.getId()));
	}

	@Test
	void canAvoidFight() {
		assertThat(sectionService.onClick(1).id())
				.isEqualTo(SectionId.book1(44));
	}

	@Test
	void hasTwoActiveOptions() {
		assertThat(sectionService.getSectionHandler(section15.getId()).getBody(playerSession.getPlayer()).allActiveClickIds()).hasSize(2);
	}

	@Test
	void containsInsult() {
		assertThat(sectionService.getSectionHandler(section15.getId()).getBody(playerSession.getPlayer()).asPlainText()).contains("insult");
	}

	@Test
	void cannotChickenOut() {
		assertThatThrownBy(() -> sectionService.onClick(0))
				.isInstanceOf(FabledBusinessException.class);
	}
}
