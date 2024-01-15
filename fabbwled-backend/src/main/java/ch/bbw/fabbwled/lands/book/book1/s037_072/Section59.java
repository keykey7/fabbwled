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
public class Section59 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 59);
    }

    /** ISNOTIMPLEMENTABLE: get Possession */
    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        Your deft fingers find a false spine of carved wood among the
                        titles in the bookcase. Pressing it, you hear a click and the
                        bookcase swings out from the wall. Beyond lies a hidden room
                        where you find a verdigris key.""")
                .choice(c -> c.text("Leave at once"), a -> a.clickableTurnTo(10))
                .choice(c -> c.text("Go upstairs to find Lauria"), a -> a.clickableTurnTo(386))
                .choice(c -> c.text("Wait for her to return"), a -> a.clickableTurnTo(534));
    }
}
