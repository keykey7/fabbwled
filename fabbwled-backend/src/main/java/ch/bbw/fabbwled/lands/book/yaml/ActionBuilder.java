package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ActionBuilder {

    public List<Action> buildActions(List<RawAction> rawActions) {
        return rawActions.stream().map(this::build).toList();
    }

    public Action build(RawAction raw) {
        var selectedKind = allActionKinds.stream().filter(actionKind -> actionKind.extractor.apply(raw) != null)
                .findFirst().orElseThrow(() -> new FabledTechnicalException("no action kind registered for action. you need to add your action to the allActionKinds list in ActionBuilder"));

        allActionKinds.forEach(actionKind -> {
            if (actionKind != selectedKind && (actionKind.extractor.apply(raw) != null)) {
                    throw new FabledTechnicalException("Invalid action. Cannot have both " + selectedKind.name + " and " + actionKind.name);

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
                }).toList();

                return new Action.Choice(choices);
            }),
            new ActionKind("acquireKeyword", RawAction::acquireKeyword, raw -> new Action.AcquireKeywordAction(raw.acquireKeyword())),
            new ActionKind("checkTickBox", RawAction::checkTickBox, raw -> new Action.CheckTickBoxAction(raw.checkTickBox())),
            new ActionKind("useResurrectionDeal", RawAction::checkTickBox, raw -> new Action.UseResurrectionDealAction(raw.useResurrectionDeal())),
            new ActionKind("acquirePossession", RawAction::acquirePossession, raw -> new Action.AcquirePosessionAction(raw.acquirePossession())),
            new ActionKind("spendShards", RawAction::spendShards, raw -> new Action.SpendShardsAction(raw.spendShards())),
            new ActionKind("turnTo", RawAction::turnTo, raw -> new Action.TurnToAction(new SectionId(1, raw.turnTo())))
    );


    private Condition buildCondition(RawCondition raw) {
        if (raw.hasTitle() != null) {
            assertNull(raw.hasKeyword(), "hasKeyword", "hasTitle");
            assertNull(raw.needsAtLeastShards(), "needsAtLeastShards", "hasTitle");
            assertNull(raw.isTickBoxDone(), "isTickBoxDone", "hasTitle");
            assertNull(raw.hasPossession(), "hasPossession", "hasTitle");
            assertNull(raw.isResurrectionPossible(), "isResurrectionPossible", "hasTitle");
            assertNull(raw.hasProfession(), "hasProfession", "hasTitle");

            return new Condition.HasTitle(raw.hasTitle());
        }

        if (raw.hasKeyword() != null) {
            assertNull(raw.needsAtLeastShards(), "needsAtLeastShards", "hasKeyword");
            assertNull(raw.isTickBoxDone(), "isTickBoxDone", "hasKeyword");
            assertNull(raw.hasPossession(), "hasPossession", "hasKeyword");
            assertNull(raw.hasProfession(), "hasProfession", "hasKeyword");
            assertNull(raw.isResurrectionPossible(), "isResurrectionPossible", "hasKeyword");

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
        if (raw.isResurrectionPossible() != null) {
            return new Condition.IsResurrectionPossible(raw.isResurrectionPossible());
        }
        if (raw.hasProfession() != null) {
            return new Condition.HasProfession(raw.hasProfession());
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
