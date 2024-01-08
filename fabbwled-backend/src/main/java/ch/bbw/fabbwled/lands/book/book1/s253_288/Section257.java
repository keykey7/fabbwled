package ch.bbw.fabbwled.lands.book.book1.s253_288;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Section257 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 257);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        The trees are closely packed, leaning together as if in conference,
                        whispering quietly among themselves. Birds twitter in the
                        distance, and slivers of sunlight lance down through the musty
                        gloom.
                        As you proceed along a forest track, you think you hear a
                        rustling in the bushes. Later, you spot a shadowy figure darting
                        through the trees – or was it your imagination? An animal
                        snuffling sound right behind you makes you spin round, but
                        there is nothing there.
                        """)
                .clickableDifficultyRollWithOptions(current, AbilityEnum.SCOUTING, 10,
                        success -> success.clickableTurnTo(630),
                        failure -> failure.clickableTurnTo(36));
    }
}
