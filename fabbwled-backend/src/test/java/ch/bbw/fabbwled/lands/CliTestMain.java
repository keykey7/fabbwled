package ch.bbw.fabbwled.lands;

import ch.bbw.fabbwled.lands.book.SectionDto;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.controller.PlayerController;
import ch.bbw.fabbwled.lands.controller.SectionController;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assumptions.assumeFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = {"logging.level.ch.bbw.fabbwled=WARN"})
@ActiveProfiles("test")
class CliTestMain {

	@Autowired
	PlayerSession playerSession;

	@Autowired
	PlayerController playerController;

	@Autowired
	SectionController sectionController;

	@BeforeAll
	static void onlyInHeadless() {
		assumeFalse(GraphicsEnvironment.isHeadless());
	}

	@Test
	void run() {
		playerSession.update(x -> x.withCurrentSection(SectionId.book1(15)));
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

	private boolean askForNextAction(PlayerSession.PlayerDto me, SectionDto section) {
		var validOptions = Stream.concat(section.body().allClickIds().stream().sorted().map(i -> "" + i),
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
			sectionController.click(new SectionController.SectionClick(Integer.parseInt(action)));
		}
		return true;
	}
}
