package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class DiceRollHelper {

    private int diceSumCount = 0;

    public int roll(int amountOfDices) {
        diceSumCount = 0;

        if (amountOfDices < 1) {
            throw new FabledTechnicalException("The amount of dices cannot be negativ or zero!");
        }

        for (int i = 0; i < amountOfDices; i++) {
            Random random = new Random();
            diceSumCount += random.ints(1, 6)
                    .findFirst()
                    .getAsInt();
        }

        return diceSumCount;
    }

    public boolean isBetweenScore(int start, int end) {
        return diceSumCount <= end && diceSumCount >= start;
    }

    public boolean hasUserThrownDice() {
        return diceSumCount > 0;
    }
}