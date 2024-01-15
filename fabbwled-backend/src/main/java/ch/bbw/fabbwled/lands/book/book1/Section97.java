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
public class Section97 implements SectionHandler {
    @Override
    public SectionId getId() {
        return new SectionId(1, 97);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        You climb down a narrow track to the beach. The sea pounds
                        the rocky shore, and the spray lashes your face. A mournful, yet
                        utterly captivating singing suddenly fills your ears. You look out
                        to sea, and spot several mermaids and mermen, cavorting in the
                        surf.
                        ‘Come, come to us…’ one of them calls in a lilting voice
                        that fills you with a yearning desire to plunge into the sea and
                        swim out to them.""")
                .clickableDifficultyRollWithOptions(current, AbilityEnum.SANCTITY, 10,
                        success -> success.clickableTurnTo(584),
                        failure -> failure.clickableTurnTo(159));
    }
}
