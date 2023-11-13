package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionDto;
import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.exception.FabledBusinessException;
import ch.bbw.fabbwled.lands.service.DiceRollHelper;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Section82 implements SectionHandler {

    private final static int AMOUNT_OF_DICES = 1;

    private final PlayerSession playerSession;
    private final DiceRollHelper diceRollHelper;

    @Override
    public SectionId getId() {
        return new SectionId(1, 82);
    }

    @Override
    public SectionNode getBody() {
        return SectionNode.root()
                .text("You are following the course of the Stinking River – and it\n" +
                        "certainly does stink, laden with sulphur as it is.")
                .clickableDice(ClickOptions.DICE.ordinal(), AMOUNT_OF_DICES)

                .activeIf(diceRollHelper.isBetweenScore(1, 2), x -> x.clickableDiceOption(ClickOptions.SCORE_1_2.ordinal(), 1, 2)
                        .text("Stung by a large golden insect. You are " +
                                "poisoned (COMBAT –1 until you find a cure)"))
                .activeIf(diceRollHelper.isBetweenScore(3, 4), x -> x.clickableDiceOption(ClickOptions.SCORE_3_4.ordinal(), 3, 4)
                        .text("Nothing happens"))
                .activeIf(diceRollHelper.isBetweenScore(5, 6), x -> x.clickableDiceOption(ClickOptions.SCORE_5_6.ordinal(), 5, 6)
                        .text("Catch a smoulder fish. Note it on your Adventure Sheet."))

                .activeIf(hasDiceOptionBeenSelected(), x -> x.text("When you are ready, you can:")
                        .choice(c -> c.text("Follow the river north"),
                                a -> a.clickableTurnTo(ClickOptions.FOLLOW_NORTH.ordinal(), 310))
                        .choice(c -> c.text("Follow the river south"),
                                a -> a.clickableTurnTo(ClickOptions.FOLLOW_SOUTH.ordinal(), 10))
                        .choice(c -> c.text("Go west to the road"),
                                a -> a.clickableTurnTo(ClickOptions.GO_WEST.ordinal(), 558))
                        .choice(c -> c.text("Go east into the countryside"),
                                a -> a.clickableTurnTo(ClickOptions.GO_EAST.ordinal(), 278))
                );
    }

    @Override
    public void onClick(int id) {
        switch (ClickOptions.values()[id]) {
            case DICE -> {
                if (diceRollHelper.hasUserThrownDice()) {
                    throw new FabledBusinessException("invalid click: already thrown a dice");
                }
                diceRollHelper.roll(AMOUNT_OF_DICES);
            }
            case SCORE_1_2 -> {
                if (!diceRollHelper.isBetweenScore(1, 2)) {
                    throw new FabledBusinessException("invalid click: 1 and 2");
                }
                // TODO minus 1 for combat
                playerSession.update(playerDto -> {
                    var newList = playerDto.withBaseStats(playerDto.baseStats());
                    return playerDto;
                });
                addClickedClickId(ClickOptions.SCORE_1_2.ordinal());
            }
            case SCORE_3_4 -> {
                if (!diceRollHelper.isBetweenScore(3, 4)) {
                    throw new FabledBusinessException("invalid click: 3 and 4");
                }
                addClickedClickId(ClickOptions.SCORE_3_4.ordinal());
            }
            case SCORE_5_6 -> {
                if (!diceRollHelper.isBetweenScore(5, 6)) {
                    throw new FabledBusinessException("invalid click: 5 and 6");
                }

                playerSession.update(playerDto -> {
                    var newList = playerDto.withPossessions(playerDto.possessions());
                    newList.possessions().add("fish");
                    return newList;
                });
                addClickedClickId(ClickOptions.SCORE_5_6.ordinal());
            }
            case FOLLOW_NORTH -> {
                if (!hasDiceOptionBeenSelected()) {
                    throw new FabledBusinessException("The Dice Option was not clicked");
                }
                playerSession.update(x -> x.withCurrentSection(SectionId.book1(310)));
            }
            case FOLLOW_SOUTH -> {
                if (!hasDiceOptionBeenSelected()) {
                    throw new FabledBusinessException("The Dice Option was not clicked");
                }
                playerSession.update(x -> x.withCurrentSection(SectionId.book1(10)));
            }
            case GO_WEST -> {
                if (!hasDiceOptionBeenSelected()) {
                    throw new FabledBusinessException("The Dice Option was not clicked");
                }
                playerSession.update(x -> x.withCurrentSection(SectionId.book1(558)));
            }
            case GO_EAST -> {
                if (!hasDiceOptionBeenSelected()) {
                    throw new FabledBusinessException("The Dice Option was not clicked");
                }
                playerSession.update(x -> x.withCurrentSection(SectionId.book1(278)));
            }
        }
    }

    @Override
    public SectionDto.SectionTicks getTicks() {
        return SectionHandler.super.getTicks();
    }

    private void addClickedClickId(int clickId) {
        playerSession.update(x -> {
            var newList = x.withPlayerClicks(x.playerClicks());
            newList.playerClicks().put(getId(), clickId);
            return newList;
        });
    }

    /**
     * Will check if the score options of the dice have already been clicked or not
     *
     * @return either true or false
     */
    private boolean hasDiceOptionBeenSelected() {
        var diceOptions = List.of(ClickOptions.SCORE_1_2.ordinal(), ClickOptions.SCORE_3_4.ordinal(), ClickOptions.SCORE_5_6.ordinal());
        return diceOptions.contains(playerSession.getPlayer().playerClicks().get(getId()));
    }

    enum ClickOptions {
        DICE,
        SCORE_1_2,
        SCORE_3_4,
        SCORE_5_6,
        FOLLOW_NORTH,
        FOLLOW_SOUTH,
        GO_WEST,
        GO_EAST
    }
}