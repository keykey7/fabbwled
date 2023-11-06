package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.FabledTestBase;
import org.junit.jupiter.api.Test;

class DiceRollHelperTest extends FabledTestBase {

    private DiceRollHelper diceRollHelper;
    @Test
    public void testRoll() {
        System.out.println(diceRollHelper.roll(1));
    }
}