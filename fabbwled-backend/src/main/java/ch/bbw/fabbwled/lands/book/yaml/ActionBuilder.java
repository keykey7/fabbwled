package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ActionBuilder {

    public List<Action> buildActions(List<RawAction> rawActions) {
        return rawActions.stream().map(this::build).collect(Collectors.toList());
    }

    public Action build(RawAction raw) {
        var selectedKind = allActionKinds.stream().filter(actionKind -> actionKind.extractor.apply(raw) != null)
                .findFirst().orElseThrow(() -> new FabledTechnicalException("no action kind registered for action. you need to add your action to the allActionKinds list in ActionBuilder"));

        allActionKinds.forEach(actionKind -> {
            if (actionKind != selectedKind) {
                if (actionKind.extractor.apply(raw) != null) {
                    throw new FabledTechnicalException("Invalid action. Cannot have both " + selectedKind.name + " and " + actionKind.name);
                }
            }
        });

        return selectedKind.builder.apply(raw);
    }

    private final List<ActionKind> allActionKinds = List.of(
            new ActionKind("text", RawAction::text, raw -> new Action.TextAction(raw.text())),
            new ActionKind("if", RawAction::if_, raw -> {
                Optional<List<Action>> else_ =
                        raw.else_() != null ? Optional.of(buildActions(raw.else_())) : Optional.empty();

                return new Action.IfAction(this.buildCondition(raw.if_()), buildActions(raw.then()), else_);
            }),
            new ActionKind("choice", RawAction::choice, raw -> {
                if (raw.choice().isEmpty()) {
                    throw new FabledTechnicalException("choice must not be empty");
                }

                var choices = raw.choice().stream().map(choice -> {
                    var actions = buildActions(choice.then());

                    return new Action.Choice.SingleChoice(choice.text(), actions);
                }).collect(Collectors.toList());

                return new Action.Choice(choices);
            }),
            new ActionKind("acquireKeyword", RawAction::acquireKeyword, raw -> new Action.AcquireKeywordAction(raw.acquireKeyword())),
            new ActionKind("checkTickBox", RawAction::checkTickBox, raw -> new Action.CheckTickBoxAction(raw.checkTickBox())),
            new ActionKind("acquirePossession", RawAction::acquirePossession, raw -> new Action.AcquirePosessionAction(raw.acquirePossession())),
            new ActionKind("spendShards", RawAction::spendShards, raw -> new Action.SpendShardsAction(raw.spendShards())),
            new ActionKind("turnTo", RawAction::turnTo, raw -> new Action.TurnToAction(new SectionId(1, raw.turnTo()))),
            new ActionKind("hasWon", RawAction::fight, raw -> new Action.Fight(raw.fight()))
    );

    private Condition buildCondition(RawCondition raw) {
        if (raw.hasTitle() != null) {
            assertNull(raw.hasKeyword(), "hasKeyword", "hasTitle");
            assertNull(raw.needsAtLeastShards(), "needsAtLeastShards", "hasTitle");
            assertNull(raw.isTickBoxDone(), "isTickBoxDone", "hasTitle");
            assertNull(raw.hasPossession(), "hasPossession", "hasTitle");

            return new Condition.HasTitle(raw.hasTitle());
        }

        if (raw.hasKeyword() != null) {
            assertNull(raw.needsAtLeastShards(), "needsAtLeastShards", "hasKeyword");
            assertNull(raw.isTickBoxDone(), "isTickBoxDone", "hasKeyword");
            assertNull(raw.hasPossession(), "hasPossession", "hasKeyword");

            return new Condition.HasKeyword(raw.hasKeyword());
        }

        if (raw.needsAtLeastShards() != null) {
            return new Condition.NeedsAtLeastShards(raw.needsAtLeastShards());
        }

        if (raw.isTickBoxDone() != null) {
            return new Condition.IsTickBoxDone(raw.isTickBoxDone());
        }
        if (raw.hasPossession() != null) {
            return new Condition.HasPossession(raw.hasPossession());
        }
        if (raw.hasWon() != null) {
            return new Condition.HasWon(raw.hasWon());
        }

        throw new FabledTechnicalException("Condition must have one property");
    }

    private void assertNull(Object value, String name, String selected) {
        if (value != null) {
            throw new FabledTechnicalException("Invalid action. Cannot have both " + name + " and " + selected);
        }
    }

    record ActionKind(String name, Function<RawAction, Object> extractor, Function<RawAction, Action> builder) {
    }




}
