package ch.bbw.fabbwled.lands.book.book1.s037_072;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section44 implements SectionHandler {

	@Override
	public SectionId getId() {
		return new SectionId(1, 44);
	}

	@Override
	public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("You decide that discretion would be the better part of valour in this case, "
                        + "and step aside. The officers laugh contemptuously, and swagger past. "
                        + "Nothing else happens tonight. ")
                .clickableTurnTo(100);
	}
}
