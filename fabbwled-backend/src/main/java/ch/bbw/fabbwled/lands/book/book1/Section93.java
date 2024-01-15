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
public class Section93 implements SectionHandler {
    @Override
    public SectionId getId() {
        return new SectionId(1, 93);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .activeIf(current.hasCodeword("Azure"), containerNode -> containerNode.clickableTurnTo(359))
                .activeElse(a -> a.text("""
                        A mad beggar, covered in sores and grime, accosts you in the
                        street. ‘O noble one, aid me for the love of the gods!’ he rants,
                        frothing at the mouth and gesticulating wildly. ‘ I need coins to
                        eat, food to spend, and blessings to wear!’
                        """)
                        .choice(b -> b.text("Ignore him and return to city centre"), c -> c.clickableTurnTo(10))
                        .choice(b -> b.clickable(c -> c.addShards(-5), c -> c.text("Give him 5 shards")), c -> c.clickableTurnTo(227))
                        .choice(b -> b.text("Bless him"), c -> c.clickableTurnTo(632)));
    }
}
