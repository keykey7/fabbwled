package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section629 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(629);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        The soldier recognises you and leads you to see the king.
                        Nergan is pleased to see you. ‘Have you succeeded in ridding us
                        of Marloes?’ he asks eagerly.
                        """)
                .activeIf(current.hasCodeword("Assasin"), a -> a.clickableTurnTo(256))
                .activeElse(a -> a.text("""
                        You say that you have not completed your mission.
                        ‘What are you doing here, then?’ There is nothing I can do
                        to help you – you will just have to use your own wits. Now go
                        – fulfill my royal command!’ says the king.
                        You are escorted to the foothills of the mountains.
                        """).clickableTurnTo(474));
    }
}
