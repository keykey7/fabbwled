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
public class Section42 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 42);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        Two hulking shapes appear out of the shadows as if from
                        nowhere. They are hideous creatures – manlike, standing on
                        two legs, but with the tail and hairy features of a gigantic rat.
                        Their yellowing teeth snap at you as they lunge for you; the
                        ratmen also wield wicked-looking shortswords in their hands.
                        ‘Gut the human!’ yells one of them in a bestial voice.
                        You must fight them, both at once, as if they were one
                        opponent.""")
                .clickableDifficultyRollWithOptions(current, AbilityEnum.COMBAT, 8,
                        success -> success.clickableTurnTo(423),
                        failure -> failure.clickableTurnTo(308));
    }
}
