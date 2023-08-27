package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.exception.FabledBusinessException;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Section44 implements SectionHandler {

	private final PlayerSession playerSession;

	@Override
	public SectionId getId() {
		return new SectionId(1, 44);
	}

	private static final int ONLY_ACTION = 0;

	@Override
	public SectionNode getBody() {
		return SectionNode.root().text("You decide that discretion would be the better part of valour in this case, "
						+ "and step aside. The officers laugh contemptuously, and swagger past. "
						+ "Nothing else happens tonight. ")
				.clickable(ONLY_ACTION, x -> x.text("Turn to ").section(100)); // capital "Turn"!
	}

	@Override
	public void onClick(int id) {
		if (id != ONLY_ACTION) {
			throw new FabledBusinessException("invalid option " + id);
		}
		playerSession.update(x -> x.withCurrentSection(SectionId.book1(100)));
	}
}
