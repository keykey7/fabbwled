package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.FabledTestBase;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.junit.jupiter.api.Test;

class DiceRollHelperTest extends FabledTestBase {

    PlayerDto playerDto = PlayerDto.empty();

    @Test
    void testRollWithThreeDices() {
        var diceRoll = playerDto.withDiceRoll(3);
        assertThat(diceRoll.isLastRollSumBetween(3, 3*6)).isTrue();
    }

    @Test
    void testRollWithOneDice() {
        var diceRoll = playerDto.withDiceRoll(1);
        assertThat(diceRoll.isLastRollSumBetween(1, 6)).isTrue();
    }
}