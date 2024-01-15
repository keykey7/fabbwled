package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.FabledTestBase;
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
		assertThat(sectionService.byId(id).id()).isEqualTo(id);
	}
}
