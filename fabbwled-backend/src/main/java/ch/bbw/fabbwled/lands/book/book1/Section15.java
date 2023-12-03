package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Section15 implements SectionHandler {
	@Override
	public SectionId getId() {
		return new SectionId(1, 15);
	}

	@Override
	public SectionNode getBody(PlayerDto current) {
		var hasTitle = current.titlesAndHonours().contains("Protector of Sokara");
		return SectionNode.root().text("Three drunken army officers accost you on the street. ")
				.activeIf(hasTitle, x -> x.text("If you have the title Protector of Sokara, ")
						.clickableTurnTo(542)
						.text(" immediately. "))
				.activeIf(!hasTitle, x -> x.text("""
								If not, read on.
								‘Sho, what have we... hic... here,’ sneers one of them drunkenly.
								‘Out of the way, you stinking dog!’ says another, shoving you in the chest.
								""")
						.choice(c -> c.text("Step out of the way"), a -> a.clickableTurnTo(44))
						.choice(c -> c.text("Return the insult"), a -> a.clickableTurnTo(266)));
	}
}
