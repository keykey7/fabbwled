package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ActionBuilder {
    public List<Action> buildActions(List<RawAction> rawActions) {
        return rawActions.stream().map(this::build).collect(Collectors.toList());
    }

    public Action build(RawAction raw) {
        if (raw.text() != null) {
            assertNull(raw.if_(), "if", "text");
            assertNull(raw.then(), "then", "text");
            assertNull(raw.else_(), "else", "text");
            assertNull(raw.turnTo(), "turnTo", "text");
            assertNull(raw.choice(), "choice", "text");
            assertNull(raw.acquireKeyword(), "acquireKeyword", "text");

            return new Action.TextAction(raw.text());
        }

        if (raw.if_() != null) {
            if (raw.then() == null) {
                throw new FabledTechnicalException("`if` action requires a `then`");
            }
            assertNull(raw.turnTo(), "turnTo", "if");
            assertNull(raw.choice(), "choice", "if");
            assertNull(raw.acquireKeyword(), "choice", "if");

            Optional<List<Action>> else_ =
                    raw.else_() != null ? Optional.of(buildActions(raw.else_())) : Optional.empty();

            return new Action.IfAction(this.buildCondition(raw.if_()), buildActions(raw.then()), else_);
        }

        if (raw.choice() != null) {
            assertNull(raw.turnTo(), "turnTo", "choice");

            if (raw.choice().isEmpty()) {
                throw new FabledTechnicalException("choice must not be empty");
            }

            var choices = raw.choice().stream().map(choice -> {
                var actions = buildActions(choice.then());

                return new Action.Choice.SingleChoice(choice.text(), actions);
            }).collect(Collectors.toList());

            return new Action.Choice(choices);
        }

        if (raw.turnTo() != null) {
            return new Action.TurnToAction(new SectionId(1, raw.turnTo()));
        }

        throw new FabledTechnicalException("Section must have one property");
    }

    private Condition buildCondition(RawCondition raw) {
        if (raw.hasTitle() != null) {
            assertNull(raw.hasKeyword(), "hasKeyword", "hasTitle");
            assertNull(raw.needsAtLeastShards(), "needsAtLeastShards", "hasTitle");

            return new Condition.HasTitle(raw.hasTitle());
        }

        if (raw.hasKeyword() != null) {
            assertNull(raw.needsAtLeastShards(), "needsAtLeastShards", "hasKeyword");

            return new Condition.HasKeyword(raw.hasKeyword());
        }

        if (raw.needsAtLeastShards() != null) {
            return new Condition.NeedsAtLeastShards(raw.needsAtLeastShards());
        }

        throw new FabledTechnicalException("Condition must have one property");
    }


    private void assertNull(Object value, String name, String selected) {
        if (value != null) {
            throw new FabledTechnicalException("Invalid action. Cannot have both " + name + " and " + selected);
        }
    }
}
