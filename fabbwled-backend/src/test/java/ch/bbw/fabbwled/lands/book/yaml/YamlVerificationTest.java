package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.book.SectionId;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

class YamlVerificationTest implements WithAssertions {
    private static final SectionId DUMMY_ID = new SectionId(1, 1);

    @Test
    void verifyEmptyChoice() {
        var action = new Action.Choice(List.of());

        assertThatThrownBy(action::simpleVerify).hasMessage("Found choice with no choices, this doesn't make sense.");
    }

    @Test
    void verifySingleChoice() {
        var action = new Action.Choice(List.of(new Action.Choice.SingleChoice(
                "hello",
                List.of(new Action.TurnToAction(DUMMY_ID))
        )));

        assertThatThrownBy(action::simpleVerify).hasMessage("Choice action only has a single choice, don't use a choice.");
    }

    @Test
    @Disabled
    void unreachableActionShouldThrow() {
        List<Action> actions = List.of(new Action.TurnToAction(DUMMY_ID), new Action.TextAction("hello"));
        var section = new YamlSection(DUMMY_ID, actions);

        assertThatThrownBy(section::verifyReachability).hasMessage("Unreachable action: TextAction[text=hello]. This action is after a terminating action like turnTo");
    }

    @Test
    @Disabled
    void nonTerminatingSectionShouldThrow() {
        List<Action> actions = List.of(new Action.TextAction("hello"));
        var section = new YamlSection(DUMMY_ID, actions);

        assertThatThrownBy(section::verifyReachability).hasMessage("Section may not terminate. Every section needs to end with turnTo on every path.");
    }

    @Test
    @Disabled
    void doubleSpaceShouldThrow() {
        var action = new Action.TextAction("Hello.  Test.");

        assertThatThrownBy(action::simpleVerify).hasMessage("Text contains double spaces. Replace all double spaces with a single space.");
    }
}
