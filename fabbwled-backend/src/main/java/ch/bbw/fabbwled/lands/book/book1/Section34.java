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
public class Section34 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 34);
    }


    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        You make it only 50 feet up the sheer rockface before you lose
                        your footing and fall to the ground.""")

                .activeIf(current.volatileSectionStore()==null,x -> x.clickable(y -> y.subtractStamina(4).withVolatileSectionStore(true), z -> z.text("Lose 4 Stamina points.")))
                .activeElse(z -> z.text("If you still live.").clickableTurnTo(658));
    }


}
