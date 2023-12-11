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
public class Section14 implements SectionHandler {
    private final PlayerSession playerSession;

    @Override
    public SectionId getId() {
        return new SectionId(1, 14);
    }



    @Override
    public SectionNode getBody(PlayerDto current) {
        playerSession.update(x -> x.subtractStamina(5));

        return SectionNode.root().text("""
                        Someone stabs you in the back.  If you
                        still live, you spin around just as a beefy, disreputable-looking
                        thug comes for you again with a long dagger.
                        ‘Get the snooping swine!’ yells the man with the eyepatch.
                        You must fight.
                        Thug, COMBAT 3, Defence 6, Stamina 13
                        If you lose, you are dead, unless you have a resurrection deal.""")
                .clickable(x -> x.subtractStamina(5), x -> x.text("Lose 5 Stamina points."))
                .activeIf( current.baseStats().combat() > 3 && current.defence() > 6 && playerSession.getPlayer().stamina() > 13, x -> x.clickableTurnTo(473))
                .activeIf(current.isResurrectionPossible(), x -> x.clickableTurnTo(current.resurrectionArrangement().sectionIdToTurnTo().sectionId()))
                .clickableTurnTo(1);
    }


}
