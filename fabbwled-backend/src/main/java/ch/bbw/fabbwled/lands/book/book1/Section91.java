package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.effects.OnAttributeRefresh;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class Section91 implements SectionHandler {

    private static final String POISON = "Golden insect (COMBAT –1)";

    @Override
    public SectionId getId() {
        return new SectionId(1, 91);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        var hasRolled = current.hasDiceRolled();
        return SectionNode.root()
                .text("He smiles and takes you into the Gambler’s Den. It is a smoke filled casino, full of all kinds of dubious characters, playing cards\n" +
                        "and dice. If you want to gamble, decide how much you want to\n" +
                        "bet, to a maximum of 20 Shards, and roll two dice.\n" +
                        "When you are ready to leave, put a tick in the box and turn to\n" +
                        "109, unless the box is already ticked, in which case turn to 100.e following the course of the Stinking River – and it " +
                        "certainly does stink, laden with sulphur as it is.")
                .activeIf(!hasRolled, roll -> roll.clickable(player -> {
                    // will not do anything because there is no input field yet
                    var updated = player.withDiceRoll(2);
                    /**var updated = player.withDiceRoll(2);
                    if (updated.isLastRollSumBetween(1, 2)) {
                        updated = updated.addPoison(POISON);
                    } else if (updated.isLastRollSumBetween(5, 6)) {
                        updated = updated.addPossession("smoulder fish");
                    }**/
                    return updated;
                }, a -> a.text("Roll two dice.")))

                .activeIf(current.isLastRollSumBetween(2, 2), x -> x.choice(
                        a -> a.text("Score 2"),
                        c -> c.text("win five times your bet"))
                )
                .activeIf(current.isLastRollSumBetween(3, 4), x -> x.choice(
                        a -> a.text("Score 3 or 4"),
                        b -> b.text("win twice your bet"))
                )
                .activeIf(current.isLastRollSumBetween(5, 9), x -> x.choice(
                        a -> a.text("Score 5 or 9"),
                        b -> b.text("lose your bet."))
                )
                .activeIf(current.isLastRollSumBetween(10, 11), x -> x.choice(
                        a -> a.text("Score 10-11"),
                        b -> b.text("win twice your bet."))
                )
                .activeIf(current.isLastRollSumBetween(12, 12), x -> x.choice(
                        a -> a.text("Score 12"),
                        b -> b.text("win five times your bet."))
                )
                .activeIf(hasRolled, x -> x.text("When you are ready, you can:")
                        .choice(c -> c.text("put a tick in the box and"),
                                a -> a.clickableTurnTo(109))
                        .choice(c -> c.text("unless the box is already ticked, in which case"),
                                a -> a.clickableTurnTo(100))
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
