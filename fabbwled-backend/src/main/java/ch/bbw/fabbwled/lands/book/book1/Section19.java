package ch.bbw.fabbwled.lands.book.book1;


import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class Section19 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 19);
    }

    @Override
    public int getMaxTicks() {
        return 3;
    }


    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        The Dragon Knights are impressed with your combat skills.
                        Your opponent comes round, ruefully rubbing his neck.
                        Grudgingly, he admits to your superior skill and hands you his
                        weapon and armour.""")
                .activeIf(current.volatileSectionStore()==null, v -> v.clickable(k -> k.addTick().withVolatileSectionStore(true), y -> y.text("Put a tick in an empty box")))
                .activeIf(current.getTicks() == this.getMaxTicks(), y -> y.clickable(o -> o.addCodeWord("Anvil"), u -> u.text("If all three boxes are now ticked, gain the codeword Anvil.")))
                .activeIf(current.volatileSectionStore()!=null, q -> q.clickable(u -> u.addPossession("ordinary sword").addPossession("heavy plate (Defence +6)"), g -> q.clickableTurnTo(276)));
    }


}
