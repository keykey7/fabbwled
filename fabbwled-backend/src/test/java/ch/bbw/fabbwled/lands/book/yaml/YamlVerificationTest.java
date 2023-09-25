package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.book.SectionId;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class YamlVerificationTest implements WithAssertions {
    @Test
    void verifyEmptyChoice() {
        var action = new Action.Choice(List.of());

        assertThatThrownBy(action::simpleVerify).hasMessage("Found choice with no choices, this doesn't make sense.");
    }

    @Test
    void verifySingleChoice() {
        var action = new Action.Choice(List.of(new Action.Choice.SingleChoice(
                "hello",
                List.of(new Action.TurnToAction(new SectionId(1, 1)))
        )));

        assertThatThrownBy(action::simpleVerify).hasMessage("Choice action only has a single choice, don't use a choice.");
    }
}
