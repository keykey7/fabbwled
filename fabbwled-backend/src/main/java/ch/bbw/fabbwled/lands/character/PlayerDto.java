package ch.bbw.fabbwled.lands.character;

import ch.bbw.fabbwled.lands.book.SectionId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * an immutable (meaning all attributes must not be editable) representation of a player and all possible state.
 */
@With
@Builder(builderMethodName = "emptyBuilder")
@Jacksonized
public record PlayerDto(String name,
                        // the currently active section we are reading
                        @NonNull SectionId currentSection,
                        // marks story progress
                        @NonNull Set<String> titlesAndHonours,
                        @NonNull RankEnum rank,
                        // mandatory profession
                        @NonNull ProfessionEnum profession,
                        // base stamina. if it goes to zero, you die
                        int stamina,
                        // aka maximum stamina. can increase on special events and usually when increasing in RANK
                        int staminaWhenUnwounded,
                        // defence is usually computed on-the-fly, so this is a volatile field
                        int defence,
                        // worship me
                        String god,
                        @With(AccessLevel.PRIVATE)
                        @NonNull Character.BaseStatsDto baseStats,
                        // Items in your inventory
                        @NonNull List<String> possessions,
                        // money, money, money... can carry infinite amounts
                        int shards,
                        // remembers all section tick-boxes (the box icon next to the number). use {@code #getTicks} to read them
                        @NonNull Map<SectionId, Integer> tickBoxes,
                        // Codewords are special markers indicating story-progress, like "if you have codeword Apple..."
                        @NonNull Set<String> codeWords,
                        boolean isResurrectionPossible,
                        // there can only ever be one resurrection deal: you can spawn here if you die
                        Resurrection resurrectionArrangement,
                        // blessings allow you to re-roll a failed difficultly check, like "Make a SANCTITY roll at Difficulty 10"
                        @NonNull Set<BlessingEnum> blessings,
                        // not officially listed on the character sheet. can be cured (by a witch for example)
                        @NonNull Set<String> poisons,
                        // not officially listed on the character sheet. can be cured (by a healer for example)
                        @NonNull Set<String> disease,
                        // not officially listed on the character sheet. can be cured (by a priest for example)
                        @NonNull Set<String> curses,
                        // sometimes you just have to store something for later, like items in a cache
                        @NonNull Map<SectionId, Serializable> persistentSectionStore,
                        // and sometimes the text of the section is a bit complicated and requires temporary storage
                        Serializable volatileSectionStore,
                        // remember the last type off difficulty roll, such that it can be rolled back using a blessing
                        AbilityEnum lastDifficultyRoll,
                        // a bunch of dice (1-6) most recently rolled
                        @NonNull List<Integer> mostRecentDiceRoll
) implements Serializable {

    public static PlayerDtoBuilder builder() {
        // just an easier way to set builder defaults than the @Builder.Defaults of lombok
        return emptyBuilder().currentSection(SectionId.book1(1))
                .titlesAndHonours(Collections.emptySet())
                .rank(RankEnum.OUTCAST) // ruleset book 1
                .profession(ProfessionEnum.WAYFARER) // ruleset book 1
                .stamina(9) // ruleset book 1
                .staminaWhenUnwounded(9) // ruleset book 1
                .shards(16) // ruleset book 1
                .possessions(java.util.List.of("spear", "leather jerkin (Defence +1)", "map")) // ruleset book 1
                .possessions(java.util.List.of())
                .tickBoxes(Map.of())
                .codeWords(Collections.emptySet())
                .blessings(Collections.emptySet())
                .poisons(Collections.emptySet())
                .disease(Collections.emptySet())
                .curses(Collections.emptySet())
                .persistentSectionStore(Collections.emptyMap())
                .mostRecentDiceRoll(java.util.List.of());
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

    @JsonIgnore
    public int getLastRollSum() {
        return mostRecentDiceRoll.stream().mapToInt(x -> x).sum();
    }

    public boolean isLastRollSumBetween(int lower, int upper) {
        var sum = getLastRollSum();
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

    public boolean hasCodeword(String codeword) {
        return codeWords.contains(codeword);
    }

    public boolean hasTitleOrHonor(String titleOrHonor) {
        return titlesAndHonours.contains(titleOrHonor);
    }

    public PlayerDto addPoison(String poison) {
        return withPoisons(Stream.concat(poisons.stream(), Stream.of(poison)).collect(Collectors.toUnmodifiableSet()));
    }

    public PlayerDto addShards(int amount) {
        return withShards(shards + amount);
    }

    public boolean hasDiceRolled() {
        return !mostRecentDiceRoll.isEmpty();
    }
}
