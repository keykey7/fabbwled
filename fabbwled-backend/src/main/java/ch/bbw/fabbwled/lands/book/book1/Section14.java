package ch.bbw.fabbwled.lands.book.book1;


import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class Section14 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 14);
    }



    @Override
    public SectionNode getBody(PlayerDto current) {

        return SectionNode.root().text("""
                        Someone stabs you in the back.  If you
                        still live, you spin around just as a beefy, disreputable-looking
                        thug comes for you again with a long dagger.
                      """)
                .activeIf(current.volatileSectionStore()==null,x -> x.clickable(p -> p.subtractStamina(5).withVolatileSectionStore(true), z -> z.text("Lose 5 Stamina points.")))
                .activeElseIf(current.volatileSectionStore()!= null && current.volatileSectionStore().equals(true), x -> x.text(""" 
                     ‘Get the snooping swine!’ yells the man with the eyepatch.
                     You must fight.
                     Thug, COMBAT 3, Defence 6, Stamina 13
                     If you lose, you are dead, unless you have a resurrection deal.""").activeIf( current.baseStats().combat() > 3 && current.defence() > 6 && current.stamina() > 13, z -> z.clickableTurnTo(473))
                        .activeIf(current.isResurrectionPossible()&&current.resurrectionArrangement()!=null, z -> {
                            if (current.resurrectionArrangement() != null) {
                                z.clickableTurnTo(current.resurrectionArrangement().sectionIdToTurnTo().sectionId());
                            }
                        })
                        .clickableTurnTo(1))
                ;
    }


}
