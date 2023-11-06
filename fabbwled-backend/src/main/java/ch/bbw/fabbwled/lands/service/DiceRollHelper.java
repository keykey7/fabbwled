package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DiceRollHelper {

    int diceSumCount = 0;

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
        System.out.println("Is between score: " + (diceSumCount <= end && diceSumCount >= start));
        return diceSumCount <= end && diceSumCount >= start;
    }

    public boolean hasUserThrownDice() {
        return diceSumCount > 0;
    }
}
