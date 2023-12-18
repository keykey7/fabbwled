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
public class Section29 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 29);
    }



    @Override
    public SectionNode getBody(PlayerDto current) {
        var hasRolled = current.hasDiceRolled();
        return SectionNode.root().text("""
                            Your ship is sailing in the coastal waters beside Yellowport.
                            There are a number of other ships, mostly merchantmen, but
                            there are also a few warships of the Sokaran Imperial Navy.
                            ‘At least we won’t be plagued by pirates with the navy
                            around,’ says the first mate.""")
                .activeIf(!hasRolled, roll -> roll.clickable(player -> player.withDiceRoll(2)
                , a -> a.text("Roll two dice.")))
                .activeIf(current.isLastRollSumBetween(1,2), x -> x.text("Storm").clickableTurnTo(613) )
                .activeIf(current.isLastRollSumBetween(5,9), x -> x.text("An uneventful voyage").clickableTurnTo(439))
                .activeIf(current.isLastRollSumBetween(10,12), x -> x.text("Sokaran war gailey").clickableTurnTo(165))
                ;
    }


}
