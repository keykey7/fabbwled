package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Section108 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 108);
    }


    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        That night, your sleep is restless. You dream a most vivid dream.
                                   You are attacked by a gigantic sea monster, a mighty octopoid
                                   thing that encircles the ship with tree-like tentacles and pulls it
                                   under the waves. You sink into the bottle-green depths until
                                   you are caught in a glowing golden net.
                                   You wake in an undersea palace of multi-coloured coral,
                                   with mermaids to attend you. They lead you past trident-armed
                                   merman guards into a great hall. Seated upon two giant shells,
                                   like thrones, are the king and queen of the deep, with green
                                   hair, sea-grey eyes, and crowns of pale gold. Shoals of iridescent
                                   angel fish dart about in an intricate flashing dance of colour,
                                   dancing for the rulers of the land beneath the waves. In awe,
                                   you bow before them.
                                   The queen indicates that you should entertain them, for they
                                   are bored! A silver flute appears in your hands.""")
                .clickableDifficultyRollWithOptions(current, AbilityEnum.CHARISMA, 11,
                        success -> success.clickableTurnTo(132),
                        failure -> failure.clickableTurnTo(457));
    }

}
