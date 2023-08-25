package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.FabledTestBase;
import ch.bbw.fabbwled.lands.book.FabledSection;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SectionServiceTest extends FabledTestBase {

	@Autowired
	SectionService sectionService;

	@Test
	void byId() {
		var id = SectionId.book1(15);
		assertThat(sectionService.byId(id)).extracting(FabledSection::getId).isEqualTo(id);
	}

	@Test
	void byIdInvalid() {
		var id = SectionId.book1(680);
		assertThatThrownBy(() -> sectionService.byId(id)).isInstanceOf(FabledTechnicalException.class)
				.hasMessageContaining("" + id.sectionId());
	}
}
