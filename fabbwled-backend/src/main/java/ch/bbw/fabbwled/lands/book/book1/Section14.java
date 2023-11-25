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
public class Section14 implements SectionHandler {
    private final PlayerSession playerSession;

    @Override
    public SectionId getId() {
        return new SectionId(1, 14);
    }



    @Override
    public SectionNode getBody() {
        return SectionNode.root().text("""
                        Someone stabs you in the back. Lose 5 Stamina points. If you
                        still live, you spin around just as a beefy, disreputable-looking
                        thug comes for you again with a long dagger.
                        ‘Get the snooping swine!’ yells the man with the eyepatch.
                        You must fight.
                        Thug, COMBAT 3, Defence 6, Stamina 13
                        If you lose, you are dead, unless you have a resurrection deal.""")
                .clickableTurnTo(1,473);
    }

    @Override
    public void onClick(int id) {
        playerSession.update(x -> x.withStamina(playerSession.getPlayer().stamina() - 5));
        var nextSection = switch (ClickOptions.values()[id]) {
            case FIGHT_TO_NEXT_SECTION -> {
                if(playerSession.getPlayer().baseStats().combat() > 3 && playerSession.getPlayer().getDefence() > 6 && playerSession.getPlayer().stamina() > 13) {
                    yield SectionId.book1(473);
                }
                // TODO set gameover variable where player is reset
                yield SectionId.book1(1);

            }
        };
        playerSession.update(x -> x.withCurrentSection(nextSection));
    }

    enum ClickOptions {
        FIGHT_TO_NEXT_SECTION
    }
}