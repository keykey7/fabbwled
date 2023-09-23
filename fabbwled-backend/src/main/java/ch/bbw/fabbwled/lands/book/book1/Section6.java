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
public class Section6 implements SectionHandler {
    private final PlayerSession playerSession;

    @Override
    public SectionId getId() {
        return new SectionId(1, 6);
    }



    @Override
    public SectionNode getBody() {
        return SectionNode.root().text("""
                        The chest springs open with a click.
                        Inside you find 60 Shards, a mandolin  (CHARISMA  +1),  and  a  potion  of  healing.
                        The potion can be used once, at any time (even in combat) to restore 5  Stamina  points.
                        There  is  also  an  ancient  religious  text  about the  gods  of  the  Uttaku,  called  the  scroll  of  Ebron,
                        which reveals that one of the gods of the Uttakin is called Ebron, and that he has fourteen angles.""")

                .clickableTurnTo(4,10);
    }

    @Override
    public void onClick(int id) {
        var nextSection = switch (ClickOptions.values()[id]) {
            case NEXT_SECTION -> {
                playerSession.update(x -> x.withShards(x.shards().addShards(60)));
                List<String> currentPossessions = playerSession.getPlayer().possessions();
                List<String> currentPossessionsForHealing = playerSession.getPlayer().possessions();
                currentPossessionsForHealing.add("potion of healing");
                currentPossessions.add("mandolin");
                playerSession.update(x -> x.withPossessions(currentPossessions).withBaseStats(x.baseStats().withCharisma(x.baseStats().charisma()+1)));
                playerSession.update(x -> x.withPossessions(currentPossessionsForHealing));
                yield SectionId.book1(10);
            }
        };
        playerSession.update(x -> x.withCurrentSection(nextSection));
    }

    enum ClickOptions {
        NEXT_SECTION
    }
}

