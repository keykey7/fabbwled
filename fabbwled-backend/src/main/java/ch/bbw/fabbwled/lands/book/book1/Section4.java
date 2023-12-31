package ch.bbw.fabbwled.lands.book.book1;


import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Section4 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 4);
    }


    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        The  priests of Alvir and  Valmir  are  overjoyed  that  you  have returned the golden net.
                                """)
                .activeIf(current.volatileSectionStore()== null,y -> y.clickable(p -> p.addShards(100).addPossession("trident  (COMBAT  +1)").withVolatileSectionStore(true), x -> x.text("     The high priest rewards you with 100 Shards  and  a  magic  weapon,  a  rune-engraved  trident. Note the weapon, a trident  (COMBAT  +1),  on your  Adventure  Sheet.")))

                .activeElse(x -> x.clickableTurnTo(220));
    }


}
