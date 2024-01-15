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
public class Section64 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 64);
    }

    /** ISNOTIMPLEMENTABLE: get optional possession */
    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        Your amateurish tinkering sets the trap off, and the chest
                        explodes! You take the full force of the blast, you also find
                        that the contents of the chest have been vaporized – except
                        for a sturdy metal scroll case, containing a piece of ancient
                        religious text about the gods of Uttaku. You discover from the
                        scroll that one of the gods of the Uttakin is called Ebron, and
                        that he has fourteen angles. Note the scroll of Ebron on your 
                        Adventure Sheet if you wish to take it.""")
                .clickableTurnTo(10);
    }
}
