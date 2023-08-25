package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SimpleGoto;
import ch.bbw.fabbwled.lands.book.FabledSection;
import ch.bbw.fabbwled.lands.book.SectionId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Section15 implements FabledSection {
	@Override
	public SectionId getId() {
		return new SectionId(1, 15);
	}

	@Override
	public String getText() {
		return """
				Three drunken army officers accost you on the street. If you
				have the title Protector of Sokara, turn to 542 immediately. If
				not, read on.
				‘Sho, what have we... hic... here,’ sneers one of them
				drunkenly.
				‘Out of the way, you stinking dog!’ says another, shoving
				you in the chest.
				""";
	}

	@Override
	public List<SimpleGoto> getGoto() {
		return List.of(new SimpleGoto("Step out of the way", SectionId.book1(44)),
				new SimpleGoto("Return the insult", SectionId.book1(266)));
	}
}
