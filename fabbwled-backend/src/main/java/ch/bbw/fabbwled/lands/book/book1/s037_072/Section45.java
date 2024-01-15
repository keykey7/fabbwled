package ch.bbw.fabbwled.lands.book.book1.s037_072;


import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class Section45 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 45);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        The little girl runs off before you can talk to her. You thread
                        your way through the pitted tombstones and brooding crypts of
                        the cemetery, under a pale moon that bathes the graveyard in a
                        sickly, pallid light.
                        Suddenly, a foul stench fills your nostrils, and a figure rises
                        up out of the shadows! Yellow eyes glow with feral blood-lust,
                        and the creature sinks its black teeth into your arm before you
                        can react.
                        The ghoul, a rotting, walking corpse, lunges for you again.
                        """)
                .choice(c -> c.text("Fight it"), a -> a.clickableTurnTo(617))
                .choice(c -> c.text("Invoke the power of the gods"), a -> a.clickableTurnTo(144))
                .choice(c -> c.text("Use salt and iron filings"), a -> a.clickableTurnTo(303));
    }
}
