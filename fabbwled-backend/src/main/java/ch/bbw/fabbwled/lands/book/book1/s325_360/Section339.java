package ch.bbw.fabbwled.lands.book.book1.s325_360;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;

public class Section339 implements SectionHandler {
    @Override
    public SectionId getId() {
        return new SectionId(1, 339);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return null;
        //        return SectionNode.root().text("""
//                        You find a burned-out house in the poor quarter where a trader
//                        has set up a stall, selling ash and debris. The merchant, a
//                        weaselly-looking old woman, is screeching, ‘Ashes, ashes from
//                        the house of a sorceress! Fifteen Shards a packet!’
//
//                        If you want to buy some, cross off 15 Shards and note the
//                        ashes on your Adventure Sheet. When you are ready, you head
//                        back to the center of the town.
//                    """).
    }
}
