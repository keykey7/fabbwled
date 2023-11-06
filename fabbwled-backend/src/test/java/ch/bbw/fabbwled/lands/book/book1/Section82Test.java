package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.FabledTestBase;
import ch.bbw.fabbwled.lands.exception.FabledBusinessException;
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
    void hasThreeOptions() {
        assertThat(sectionService.getSectionHandler(section82.getId()).getBody().allClickIds()).hasSize(4);
    }

    @Test
    void testBody() {

    }

    @Test
    void containsInsult() {
        assertThat(sectionService.getSectionHandler(section82.getId()).getBody().asPlainText()).contains("insult");
    }

    @Test
    void cannotChickenOut() {
        assertThatThrownBy(() -> sectionService.onClick(Section15.ClickOptions.PROTECTOR.ordinal()))
                .isInstanceOf(FabledBusinessException.class);
    }
}