package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.FabledTestBase;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import ch.bbw.fabbwled.lands.service.SectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class Section82Test extends FabledTestBase {
    @Autowired
    Section82 section82;

    @Autowired
    PlayerSession playerSession;

    @Autowired
    SectionService sectionService;

    @BeforeEach
    public void setToSection82() {
        playerSession.update(x -> x.withCurrentSection(section82.getId()));
    }

    @Test
    void hasOneStartingOption() {
        assertThat(sectionService.getSectionHandler(section82.getId()).getBody().allClickIds()).hasSize(4);
    }

    @Test
    void setOptionEnabledBasedOnNumber() {
        sectionService.onClick(0);

    }
}