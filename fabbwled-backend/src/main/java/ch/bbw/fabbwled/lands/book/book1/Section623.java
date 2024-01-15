package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;

public class Section623 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(623);
    }

    /* Shop function not provided */
    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        You clamber down a ladder into a long, low hall, the Venefax Market.
                        Items with no purchase price listed are not available locally.
                        Armour To buy To sell
                        Leather (Defence +1) 50 Shards 45 Shards
                        Ring mail (Defence +2) – 90 Shards
                        Weapons (sword, axe, etc) To buy To sell
                        Without COMBAT bonus 50 Shards 40 Shards
                        COMBAT bonus +1 – 200 Shards
                        Other items To buy To sell
                        Rope 50 Shards 45 Shards
                        Lantern 100 Shards 90 Shards
                        Climbing gear 100 Shards 90 Shards
                        Scorpion antidote 100 Shards 90 Shards
                         When you are finished, turn to 427 .
                        """)
                .clickableTurnTo(427)
                ;
    }
}
