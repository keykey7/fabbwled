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
public class Section53 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 53);
    }

    /** ISNOTIMPLEMENTABLE: get optional possession */
    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        The creature bursts open in death, spilling a black inky cloud
                        into the water. The sac in which this ink is kept falls free from
                        its body. You can take the ink sac if you wish. You also find
                        coral jewellery worth about 15 Shards. Nothing else occurs during
                        your foray into the depths, so you return to land. You climb back
                        up the path that leads to the clifftop tor without incident.
                        """)
                .choice(c -> c.text("Take the road to Trefoille"), a -> a.clickableTurnTo(602))
                .choice(c -> c.text("Take the road to Marlock City"), a -> a.clickableTurnTo(166));
    }
}
