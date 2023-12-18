package ch.bbw.fabbwled.lands.book.book1;




import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Section6 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 6);
    }



    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        The chest springs open with a click.
                        There  is  also  an  ancient  religious  text  about the  gods  of  the  Uttaku,  called  the  scroll  of  Ebron,
                        which reveals that one of the gods of the Uttakin is called Ebron, and that he has fourteen angles.""")
                .activeIf(current.volatileSectionStore()==null,p -> p.clickable(x -> x.addShards(60).addPossession("potion of healing").withVolatileSectionStore(true).addPossession("mandolin  (CHARISMA  +1)"), x -> x.text("Inside you find sixty Shards, a mandolin  ( CHARISMA  +one ),  and  a  potion  of  healing. The potion can be used once, at any time (even in combat) to restore five  Stamina  points.")))
                .activeElse(x -> x.clickableTurnTo(10));
    }

}

