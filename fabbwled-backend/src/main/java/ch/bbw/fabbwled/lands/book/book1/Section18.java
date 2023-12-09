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
public class Section18 implements SectionHandler {
    private final PlayerSession playerSession;

    @Override
    public SectionId getId() {
        return new SectionId(1, 18);
    }



    @Override
    public SectionNode getBody(PlayerDto current) {
        playerSession.update(x -> x.addShards(15));
        return SectionNode.root().text("""
                        You spin them a tale about how your poor brother, a mercenary
                        in Grieve Marlock’s personal guard, lost his legs in the fight to
                        overthrow the old king, and that you have spent all your money
                        on looking after him. Several of the militia are brought to tears
                        by your eloquent speech – they end up having a whip-round
                        among themselves for your brother, and they give you 15
                        Shards! Chuckling to yourself, you return to the city centre.""")
                .clickableTurnTo(10);
    }
}
