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
public class Section15 implements SectionHandler {
	private final PlayerSession playerSession;

	@Override
	public SectionId getId() {
		return new SectionId(1, 15);
	}

	private boolean isProtector() {
		return playerSession.getPlayer().titlesAndHonours().contains("Protector of Sokara");
	}

	@Override
	public SectionNode getBody() {
		var hasTitle = isProtector();
		return SectionNode.root().text("Three drunken army officers accost you on the street. ")
				.activeIf(hasTitle, x -> x.text("If you have the title Protector of Sokara, ")
						.clickableTurnTo(ClickOptions.PROTECTOR.ordinal(), 542)
						.text(" immediately. "))
				.activeIf(!hasTitle, x -> x.text("""
								If not, read on.
								‘Sho, what have we... hic... here,’ sneers one of them drunkenly.
								‘Out of the way, you stinking dog!’ says another, shoving you in the chest.
								""")
						.choice(c -> c.text("Step out of the way"),
								a -> a.clickableTurnTo(ClickOptions.STEP_OUT.ordinal(), 44))
						.choice(c -> c.text("Return the insult"),
								a -> a.clickableTurnTo(ClickOptions.INSULT.ordinal(), 266)));
	}

	@Override
	public void onClick(int id) {
		var nextSection = switch (ClickOptions.values()[id]) {
			case PROTECTOR -> {
				if (!isProtector()) {
					throw new FabledBusinessException("invalid click: not protector");
				}
				yield SectionId.book1(542);
			}
			case STEP_OUT -> {
				if (isProtector()) {
					throw new FabledBusinessException("invalid click: protector");
				}
				yield SectionId.book1(44);
			}
			case INSULT -> {
				if (isProtector()) {
					throw new FabledBusinessException("invalid click: protector");
				}
				yield SectionId.book1(266);
			}
		};
		playerSession.update(x -> x.withCurrentSection(nextSection));
	}

	enum ClickOptions {
		PROTECTOR,
		STEP_OUT,
		INSULT
	}
}
