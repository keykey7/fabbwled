package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.FabledTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class DiceRollHelperTest extends FabledTestBase {

    @Autowired
    private DiceRollHelper diceRollHelper;
    @Test
    public void testRollWithThreeDices() {
        var diceRoll = diceRollHelper.roll(3);
        assertThat(diceRoll >= 3 && diceRoll <= 18);
    }

    @Test
    public void testRollWithOneDice() {
        var diceRoll = diceRollHelper.roll(1);
        assertThat(diceRoll >= 1 && diceRoll <= 6);
    }
}