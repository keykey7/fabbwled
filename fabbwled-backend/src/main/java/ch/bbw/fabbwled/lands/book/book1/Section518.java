package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section518 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(518);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        var hasRolled = current.hasDiceRolled();
        return SectionNode.root()
                .text("""
                        You are on the road between the Citadel of Velis Corin and
                        Fort Mereth. This is quite rarely travelled – the military
                        guardposts are few and far between. Roll one die.
                        """)
                .activeIf(!hasRolled, roll -> roll.clickable(player -> {
                    var updated = player.withDiceRoll(1);
                    if (updated.isLastRollSumBetween(1, 2)) {
                        if (updated.hasEnoughShards(10)) {
                            updated = updated.addShards(-10);
                        }
                    } else if (updated.isLastRollSumBetween(5, 6)) {
                        if (updated.stamina() < 3) {
                            updated = updated.setStamina(3);
                        }
                    }
                    return updated;
                }, a -> a.text("Roll a die.")))

                .activeIf(current.isLastRollSumBetween(1, 2), x -> x.choice(
                        a -> a.text("Score 1 or 2"),
                        c -> c.text("Lose 10 Shards gambling (if you have it)."))
                ).activeIf(current.isLastRollSumBetween(3, 4), x -> x.choice(
                        a -> a.text("Score 3 or 4"),
                        c -> c.text("Nothing happens."))
                )
                .activeIf(current.isLastRollSumBetween(5, 6), x -> x.choice(
                        a -> a.text("Score 5 or 6"),
                        b -> b.text("A sweet spring heals you of up to 3 Stamina points."))
                )

                .activeIf(hasRolled, x -> x.text("When you are ready, you can:")
                        .choice(c -> c.text("Go to Disaster Bay"),
                                a -> a.clickableTurnTo(495))
                        .choice(c -> c.text("Head for the Citadel of Velis Corin"),
                                a -> a.clickableTurnTo(271))
                        .choice(c -> c.text("Travel to Fort Mereth"),
                                a -> a.clickableTurnTo(299))
                        .choice(c -> c.text("Go south west into wild country"),
                                a -> a.clickableTurnTo(60))
                );
    }
}

