package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section639 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(639);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode
                .root()
                .text("""
                        Heavy black clouds race towards you across the sky, whipping
                        the waves into a frenzy. The crew mutter among themselves
                        fearfully. If you have the blessing of Alvir and Valmir, which
                        confers Safety from Storms, you can ignore the storm. Cross off
                        your blessing and turn to 507 . Otherwise the storm hits with full
                        fury.
                        Roll one die if your ship is a barque, two dice if it is a
                        brigantine, or three dice if it is a galleon. Add 1 to the roll if you
                        have a good crew; add 2 if you have excellent crew.
                        """)
                .choice(a -> a.text("Score 1-3 Ship sinks turn to 219"), b -> b.clickableTurnTo(219))
                .choice(a -> a.text("Score 4-5 The mast splits turn to 67"), b -> b.clickableTurnTo(67))
                .choice(a -> a.text("Score 6-20 You weather the storm turn to 507"), b -> b.clickableTurnTo(507));
    }
}
