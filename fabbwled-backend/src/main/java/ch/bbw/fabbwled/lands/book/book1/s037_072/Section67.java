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
public class Section67 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 67);
    }

    /** ISNOTIMPLEMENTABLE: ship with cargo */
    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        Your ship is thrown about like flotsam and jetsam. When the
                        storm subsides, you take stock. Much has been swept overboard
                        – you lose 1 Cargo Unit, if you had any, of your choice. Also,
                        the ship has been swept way off course and the mate has no idea
                        where you are. ‘We’re lost at sea, Cap’n!’ he moans.
                        """)
                .clickableTurnTo(90);
    }
}
