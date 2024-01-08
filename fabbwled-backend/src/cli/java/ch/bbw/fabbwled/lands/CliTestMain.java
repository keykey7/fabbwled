package ch.bbw.fabbwled.lands;

import ch.bbw.fabbwled.lands.book.SectionDto;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.controller.PlayerController;
import ch.bbw.fabbwled.lands.controller.SectionController;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.swing.*;
import java.util.Comparator;
import java.util.stream.Stream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = {"logging.level.ch.bbw.fabbwled=WARN"})
@ActiveProfiles("test")
class CliTestMain {

	@Autowired
	PlayerSession playerSession;

	@Autowired
	PlayerController playerController;

	@Autowired
	SectionController sectionController;

    static {
        System.setProperty("java.awt.headless", "false");
    }

	@Test
	void run() {
		playerSession.update(x -> x.withCurrentSection(SectionId.book1(30)).withShards(1000));
		while (true) {
			var me = playerController.whoami();
			var section = sectionController.byId(me.currentSection().bookId(), me.currentSection().sectionId());
			System.out.println(section.asPlainText());
			var shouldContinue = askForNextAction(me, section);
			if (!shouldContinue) {
				break;
			}
		}
	}

	private boolean askForNextAction(PlayerDto me, SectionDto section) {
        var validOptions = Stream.concat(section.body().allActiveClickIds().stream()
                        .sorted(Comparator.comparingInt(SectionNode.ClickableNode::clickId))
                        .map(i -> i.asPlainText().replaceAll("\033\\[\\d+m", "")),
                Stream.of("stats")).toList();
		var selectedId = JOptionPane.showOptionDialog(null,
				"Pick an option",
				"What to do next",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,     //do not use a custom Icon
				validOptions.toArray(),  //the titles of buttons
				validOptions.get(0));
		if (selectedId == JOptionPane.CLOSED_OPTION) {
			return false;
		}
		var action = validOptions.get(selectedId);
		if (action.equals("stats")) {
			System.out.println(me.toString()); // not very nice, but it works
		}
		else {
            var clickId = Integer.parseInt(action.replaceAll(".+\\[(\\d+)]", "$1"));
			sectionController.click(new SectionController.SectionClick(clickId));
		}
		return true;
	}
}
