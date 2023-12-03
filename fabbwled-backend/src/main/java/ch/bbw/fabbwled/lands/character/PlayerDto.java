package ch.bbw.fabbwled.lands.character;

import ch.bbw.fabbwled.lands.book.SectionId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * an immutable (meaning all attributes must not be editable) representation of a player and all possible state.
 * @param tickBoxes remembers all section tick-boxes (the box icon next to the number). use {@code #getTicks} to read.
 * @param mostRecentDiceRoll a set of dice (one or more) that were thrown most recently. empty list if no throw.
 */
@With
@Builder(builderMethodName = "emptyBuilder")
@Jacksonized
public record PlayerDto(String name,
                        @NonNull SectionId currentSection,
                        @NonNull Set<String> titlesAndHonours,
                        @NonNull RankEnum rank,
                        ProfessionEnum profession,
                        int stamina,
                        int staminaWhenUnwounded,
                        int defence,
                        String god,
                        @With(AccessLevel.PRIVATE)
                        @NonNull Character.BaseStatsDto baseStats,
                        @NonNull List<String> possessions,
                        int shards,
                        @NonNull Map<SectionId, Integer> tickBoxes,
                        @NonNull Set<String> codeWords,
                        boolean isResurrectionPossible,
                        Resurrection resurrectionArrangement,
                        @NonNull Set<BlessingEnum> blessings,
                        @NonNull Set<String> poisons,
                        @NonNull Set<String> disease,
                        @NonNull Set<String> curses,
                        @NonNull List<Integer> mostRecentDiceRoll
) implements Serializable {

    public static PlayerDtoBuilder builder() {
        // just an easier way to set builder defaults than the @Builder.Defaults of lombok
        return emptyBuilder().currentSection(SectionId.book1(1))
                .titlesAndHonours(Collections.emptySet())
                .rank(RankEnum.OUTCAST) // ruleset book 1
                .stamina(9) // ruleset book 1
                .staminaWhenUnwounded(9) // ruleset book 1
                .shards(16) // ruleset book 1
                .possessions(List.of("spear", "leather jerkin (Defence +1)", "map")) // ruleset book 1
                .possessions(List.of())
                .tickBoxes(Map.of())
                .codeWords(Collections.emptySet())
                .blessings(Collections.emptySet())
                .poisons(Collections.emptySet())
                .disease(Collections.emptySet())
                .curses(Collections.emptySet())
                .mostRecentDiceRoll(List.of());
    }

    public static PlayerDto empty() {
        return PlayerDto.builder()
                .baseStats(Character.BaseStatsDto.all1())
                .build();
    }

    public PlayerDto withCurrentSectionId(int sectionId) {
        return withCurrentSection(new SectionId(currentSection.bookId(), sectionId));
    }

    public PlayerDto withDiceRoll(int amount) {
        return withMostRecentDiceRoll(new Random().ints(amount, 1, 7).boxed().toList());
    }

    public boolean isLastRollSumBetween(int lower, int upper) {
        var sum = mostRecentDiceRoll.stream().mapToInt(x -> x).sum();
        return lower <= sum && sum <= upper;
    }

    public PlayerDto addTick() {
        var tmp = new HashMap<>(tickBoxes);
        tmp.merge(currentSection, 1, Integer::sum);
        return withTickBoxes(Collections.unmodifiableMap(tmp));
    }

    public int getTicks() {
        return tickBoxes.getOrDefault(currentSection, 0);
    }

    public PlayerDto addPossession(String item) {
        return withPossessions(Stream.concat(possessions.stream(), Stream.of(item)).toList());
    }

    public PlayerDto withStats(UnaryOperator<Character.BaseStatsDto> mod) {
        return withBaseStats(mod.apply(baseStats));
    }

    public PlayerDto removePossession(String item) {
        var tmp = new ArrayList<>(possessions);
        tmp.remove(item);
        return withPossessions(Collections.unmodifiableList(tmp));
    }

    public boolean hasUserThrownDice() {
        return !mostRecentDiceRoll.isEmpty();
    }
}
