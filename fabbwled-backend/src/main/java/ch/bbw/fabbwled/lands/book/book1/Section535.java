package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.character.ProfessionEnum;
import org.springframework.stereotype.Component;

@Component
public class Section535 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(535);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        The House of Priests is an impressive building, a roundhouse of
                        multi-coloured bricks. The myriad colours give it a bizarre and
                        garish look, calculated to unsettle the visitor. Inside, a hundred
                        offices team with the administration of the polytheistic religion
                        of Sokara and Golnir.
                        """)
                .activeIf(current.profession() == ProfessionEnum.PRIEST, a -> a
                        .text("If you are a Priest, ")
                        .clickableTurnTo(9)
                        .text("."))
                .activeElse(a -> a
                        .text("If not, there is little for you here, ")
                        .clickableTurnTo(100)
                        .text(".")
                );
    }
}
