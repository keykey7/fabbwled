package ch.bbw.fabbwled.lands.book.book1;


import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.exception.FabledBusinessException;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Section4 implements SectionHandler {
    private final PlayerSession playerSession;

    @Override
    public SectionId getId() {
        return new SectionId(1, 4);
    }



    @Override
    public SectionNode getBody() {
        return SectionNode.root().text("""
                        The  priests  of  Alvir  and  Valmir  are  overjoyed  that  you  have returned the golden net.
                                The high priest rewards you with 100 Shards  and  a  magic  weapon,  a  rune-engraved  trident. 
                                Note  the weapon,  a  trident  (COMBAT  +1),  on  your  Adventure  Sheet. """)

                .clickableTurnTo(1,220);
    }

    @Override
    public void onClick(int id) {
        var nextSection = switch (ClickOptions.values()[id]) {
            case NEXT_SECTION -> {
                List<String> currentPossessions = playerSession.getPlayer().possessions();
                currentPossessions.add("trident");
                playerSession.update(x -> x.withShards(x.shards().addShards(100)).withPossessions(currentPossessions).withBaseStats(x.baseStats().withCombat(x.baseStats().combat()+1)));
                yield SectionId.book1(220);
            }
        };
        playerSession.update(x -> x.withCurrentSection(nextSection));
    }

    enum ClickOptions {
        NEXT_SECTION
    }
}