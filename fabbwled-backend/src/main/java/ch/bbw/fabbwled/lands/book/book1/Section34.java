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
public class Section34 implements SectionHandler {
    private final PlayerSession playerSession;

    @Override
    public SectionId getId() {
        return new SectionId(1, 34);
    }


    @Override
    public SectionNode getBody() {
        return SectionNode.root().text("""
                        You make it only 50 feet up the sheer rockface before you lose
                        your footing and fall to the ground. Lose 4 Stamina points. If
                        you still live, turn to 658 where you can try again, if you like.""")

                .clickableTurnTo(1, 658);
    }

    @Override
    public void onClick(int id) {
        var nextSection = switch (ClickOptions.values()[id]) {
            case NEXT_SECTION -> {
                if(playerSession.getPlayer().stamina() > 4) {
                    playerSession.update(x -> x.withStamina(x.stamina()-4));
                    yield SectionId.book1(658);
                }
                yield SectionId.book1(1);

            }
        };
        playerSession.update(x -> x.withCurrentSection(nextSection));
    }

    enum ClickOptions {
        NEXT_SECTION
    }
}