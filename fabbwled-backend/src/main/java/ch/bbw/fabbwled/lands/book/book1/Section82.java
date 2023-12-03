package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.effects.OnAttributeRefresh;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class Section82 implements SectionHandler {

    private static final String POISON = "Golden insect (COMBAT –1)";

    @Override
    public SectionId getId() {
        return new SectionId(1, 82);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        var hasRolled = current.hasDiceRolled();
        return SectionNode.root()
                .text("You are following the course of the Stinking River – and it " +
                        "certainly does stink, laden with sulphur as it is.")
                .activeIf(!hasRolled, roll -> roll.clickable(player -> {
                    var updated = player.withDiceRoll(1);
                    if (updated.isLastRollSumBetween(1, 2)) {
                        updated = updated.addPoison(POISON);
                    } else if (updated.isLastRollSumBetween(5, 6)) {
                        updated = updated.addPossession("smoulder fish");
                    }
                    return updated;
                }, a -> a.text("Roll a die.")))

                .activeIf(current.isLastRollSumBetween(1, 2), x -> x.choice(
                        a -> a.text("Score 1 or 2"),
                        c -> c.text("Stung by a large golden insect. You are " +
                                "poisoned (COMBAT –1 until you find a cure)"))
                )
                .activeIf(current.isLastRollSumBetween(3, 4), x -> x.choice(
                        a -> a.text("Score 3 or 4"),
                        b -> b.text("Nothing happens"))
                )
                .activeIf(current.isLastRollSumBetween(5, 6), x -> x.choice(
                        a -> a.text("Score 5 or 6"),
                        b -> b.text("Catch a smoulder fish. Note it on your Adventure Sheet."))
                )

                .activeIf(hasRolled, x -> x.text("When you are ready, you can:")
                        .choice(c -> c.text("Follow the river north"),
                                a -> a.clickableTurnTo(310))
                        .choice(c -> c.text("Follow the river south"),
                                a -> a.clickableTurnTo(10))
                        .choice(c -> c.text("Go west to the road"),
                                a -> a.clickableTurnTo(558))
                        .choice(c -> c.text("Go east into the countryside"),
                                a -> a.clickableTurnTo(278))
                );
    }

    @Component
    @Order(OnAttributeRefresh.BASE_STATS)
    public static class GoldenInsectPoisonEffect implements OnAttributeRefresh {

        @Override
        public PlayerDto applyEffect(PlayerDto basePlayer) {
            if (basePlayer.poisons().contains(POISON)) {
                basePlayer = basePlayer.withStats(stats -> stats.withCombatAdd(-1));
            }
            return basePlayer;
        }
    }
}
