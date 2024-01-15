package ch.bbw.fabbwled.lands.book.book1;


import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.character.Resurrection;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;


@Component
@RequiredArgsConstructor
public class Section33 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 33);
    }


    @Override
    public SectionNode getBody(PlayerDto current) {
        boolean isInitiate = current.stamina() == current.staminaWhenUnwounded() && new HashSet<>(current.possessions()).containsAll(PlayerDto.empty().possessions()); // Here because real isInitiate would require a boolean set on every failed fight / death
        return SectionNode.root().text("""
                        Once you have arranged for resurrection you need
                        not fear death, as you will be magically restored to life here at
                        the temple. To arrange resurrection, pay the fee and write
                        ‘Temple of Tyrnai, The War-Torn Kingdom 640’ in the
                        Resurrection box on your Adventure Sheet. If you are later
                        killed, turn to 640 in The War-Torn Kingdom.
                        You can have only one resurrection arranged at any one
                        time. If you arrange another resurrection later at a different
                        temple, the original one is cancelled. Cross it off your Adventure
                        Sheet. You do not get a refund.""")
                .activeIf(current.shards() >= 200 && isInitiate,
                        x -> x.clickable(n -> n
                                        .subtractStamina(200).withResurrectionPossible(true)
                                        .withResurrectionArrangement(new Resurrection("Temple of Tyrnai, The War-Torn Kingdom 640",
                                                SectionId.book1(640))),
                                c -> c.text("Resurrection costs 200 Shards if you are an initiate.").clickableTurnTo(282)))
                .activeIf(current.shards() >= 800 && !isInitiate, x -> x
                        .clickable(n -> n.subtractStamina(800).withResurrectionPossible(true)
                                .withResurrectionArrangement(new Resurrection("Temple of Tyrnai, The War-Torn Kingdom 640",
                                        SectionId.book1(640))),
                                g -> g.text("Resurrection costs 800 Shards if you are not an initiate").clickableTurnTo(282)))
                .clickableTurnTo(282);


    }
}
