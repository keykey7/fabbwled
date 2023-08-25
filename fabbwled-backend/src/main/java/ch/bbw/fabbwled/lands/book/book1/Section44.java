package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SimpleGoto;
import ch.bbw.fabbwled.lands.book.FabledSection;
import ch.bbw.fabbwled.lands.book.SectionId;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class Section44 implements FabledSection {
	@Override
	public SectionId getId() {
		return new SectionId(1, 44);
	}

	@Override
	public String getText() {
		return """
				You decide that discretion would be the better part of valour in
				this case, and step aside. The officers laugh contemptuously, and
				swagger past. Nothing else happens tonight.
				""";
	}

	@Override
	public List<SimpleGoto> getGoto() {
		return Collections.singletonList(SimpleGoto.noDescription(SectionId.book1(100)));
	}
}
