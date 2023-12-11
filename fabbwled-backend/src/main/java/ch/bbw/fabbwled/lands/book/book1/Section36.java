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
public class Section36 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 36);
    }


    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                            Soon you realize you are completely lost in this strange, magical
                            forest. You wander around for days, barely able to find enough
                            food and water. Lose 4 Stamina points. If you still live, you
                            eventually stagger out of the forest to the coast.""")

                .clickable(x -> x.subtractStamina(4),z -> z.clickableTurnTo(128));
    }

}
