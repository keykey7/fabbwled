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
                        .text("Catch a smoulder fish. Note it on your Adventure Sheet."));
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

            }
            case SCORE_3_4 -> {
                if (!diceRollHelper.isBetweenScore(3, 4)) {
                    throw new FabledBusinessException("invalid click: 1 and 2");
                }
                //yield SectionId.book1(44);
            }
            case SCORE_5_6 -> {
                if (!diceRollHelper.isBetweenScore(5, 6)) {
                    throw new FabledBusinessException("invalid click: 1 and 2");
                }
                //yield SectionId.book1(44);
            }
        }
        ;
    }

    @Override
    public SectionDto.SectionTicks getTicks() {
        return SectionHandler.super.getTicks();
    }

    enum ClickOptions {
        DICE,
        SCORE_1_2,
        SCORE_3_4,
        SCORE_5_6,
    }
}
