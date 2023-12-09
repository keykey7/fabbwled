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
    private final PlayerSession playerSession;

    @Override
    public SectionId getId() {
        return new SectionId(1, 6);
    }



    @Override
    public SectionNode getBody(PlayerDto current) {
        playerSession.update(x -> x.addShards(60).addPossession("potion of healing").addPossession("mandolin  (CHARISMA  +1)"));
        return SectionNode.root().text("""
                        The chest springs open with a click.
                        Inside you find 60 Shards, a mandolin  ( CHARISMA  +1 ),  and  a  potion  of  healing.
                        The potion can be used once, at any time (even in combat) to restore 5  Stamina  points.
                        There  is  also  an  ancient  religious  text  about the  gods  of  the  Uttaku,  called  the  scroll  of  Ebron,
                        which reveals that one of the gods of the Uttakin is called Ebron, and that he has fourteen angles.""")
                .clickableTurnTo(10);
    }

}

