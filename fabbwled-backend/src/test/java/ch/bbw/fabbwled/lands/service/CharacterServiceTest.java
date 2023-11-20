package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.FabledTestBase;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.character.Character;
import ch.bbw.fabbwled.lands.character.ProfessionEnum;
import ch.bbw.fabbwled.lands.character.RankEnum;
import ch.bbw.fabbwled.lands.character.Resurrection;
import ch.bbw.fabbwled.lands.exception.FabledBusinessException;
import ch.bbw.fabbwled.lands.service.ShardSystem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@SpringBootTest
class CharacterServiceTest extends FabledTestBase {

    @Autowired
    CharacterService characterService;

    @Autowired
    PlayerSession testPlayerSession;


    @Test
    void byWrongBookId() {
        var id = 2;
        assertThat(characterService.getAllCharacters(id)).isEqualTo(List.of());
    }

    @Test
    void byCorrectBookId() {
        var id = 1;
        assertThat(characterService.getAllCharacters(id)).hasSize(6);
    }

    @Test
    void validateCharacterWithValidStats() {
        Character.CharacterCreateDto character = new Character.CharacterCreateDto(PlayerSession.PlayerDto.builder()
                .name("Liana The Swift")
                .currentSection(SectionId.book1(15))
                .titlesAndHonours(Collections.emptySet())
                .rank(RankEnum.OUTCAST)
                .profession(ProfessionEnum.WAYFARER)
                .stamina(9)
                .staminaWhenUnwounded(9)
                .baseStats(new Character.BaseStatsDto(2, 5, 2, 3, 6, 4))
                .possessions(List.of("spear", "leather jerkin (Defence +1)", "map"))
                .shards(new ShardSystem(16))
                .tickBoxes(Map.of())
                .codeWords(Collections.emptySet())
                .isResurrectionPossible(false)
                .god("Ebron")
                .blessings(Collections.emptySet())
                .resurrectionArrangement(new Resurrection("Resurrection Title",SectionId.book1(33))).build(),
                """
                        Liana prefers to make her home in mountain grottos
                        and woodland groves rather than in the squalid streets
                        of cities. She has the agility of a gazelle, the cunning of
                        a fox and the ferocity of an eagle. She has heard of a
                        City of Trees, deep within the forest of the Isle of
                        "Druids."""); // Create a character with valid stats
        testPlayerSession.validatePlayer(character.player());
    }

    @Test
    void validateCharacterWithInvalidStats() {
        Character.CharacterCreateDto character = new Character.CharacterCreateDto(PlayerSession.PlayerDto.builder()
                .name("Liana The Swift")
                .currentSection(SectionId.book1(15))
                .titlesAndHonours(Collections.emptySet())
                .rank(RankEnum.OUTCAST)
                .profession(ProfessionEnum.WAYFARER)
                .stamina(9)
                .baseStats(new Character.BaseStatsDto(2, 5, 2, 3, 7, 4))
                .possessions(List.of("spear", "leather jerkin (Defence +1)", "map"))
                .shards(new ShardSystem(16))
                .tickBoxes(Map.of())
                .codeWords(Collections.emptySet())
                .isResurrectionPossible(false)
                .staminaWhenUnwounded(9)
                .blessings(Collections.emptySet())
                .resurrectionArrangement(new Resurrection("Resurrection Title",SectionId.book1(33))).build(),

                """
                        Liana prefers to make her home in mountain grottos
                        and woodland groves rather than in the squalid streets
                        of cities. She has the agility of a gazelle, the cunning of
                        a fox and the ferocity of an eagle. She has heard of a
                        City of Trees, deep within the forest of the Isle of
                        "Druids.""");  // Create a character with invalid stats
        assertThatThrownBy(() -> testPlayerSession.validateInitialCreation(character.player())).isInstanceOfAny(FabledBusinessException.class); // Expect the validation error message
    }

    @Test
    void validateCharacterWithLargePossessionSize() {
        Character.CharacterCreateDto character = new Character.CharacterCreateDto( PlayerSession.PlayerDto.builder()
                .name("Liana The Swift")
                .currentSection(SectionId.book1(15))
                .titlesAndHonours(Collections.emptySet())
                .rank(RankEnum.OUTCAST)
                .profession(ProfessionEnum.WAYFARER)
                .stamina(9)
                .baseStats(new Character.BaseStatsDto(2, 5, 2, 3, 6, 4))
                .possessions(List.of("spear", "leather jerkin (Defence +1)", "map", "sword", "shield", "sword", "shield", "sword", "shield", "sword", "shield", "sword", "shield"))
                .shards(new ShardSystem(16))
                .tickBoxes(Map.of())
                .codeWords(Collections.emptySet())
                .isResurrectionPossible(false)
                .blessings(Collections.emptySet())
                .staminaWhenUnwounded(9)
                .resurrectionArrangement(new Resurrection("Resurrection Title",SectionId.book1(33))).build(),

                """
                        Liana prefers to make her home in mountain grottos
                        and woodland groves rather than in the squalid streets
                        of cities. She has the agility of a gazelle, the cunning of
                        a fox and the ferocity of an eagle. She has heard of a
                        City of Trees, deep within the forest of the Isle of
                        "Druids."""
        );// Create a character with a large possession size
        assertThatThrownBy(() -> testPlayerSession.validatePlayer(character.player())).isInstanceOfAny(FabledBusinessException.class); // Expect the validation error message
    }

    @Test
    void validateCharacterWithNonOutcastRank() {
        Character.CharacterCreateDto character = new Character.CharacterCreateDto(PlayerSession.PlayerDto.builder()
                .name("Liana The Swift")
                .currentSection(SectionId.book1(15))
                .titlesAndHonours(Collections.emptySet())
                .rank(RankEnum.COMMONER)
                .profession(ProfessionEnum.WAYFARER)
                .stamina(9)
                .baseStats(new Character.BaseStatsDto(2, 5, 2, 3, 6, 4))
                .possessions(List.of("spear", "leather jerkin (Defence +1)", "map"))
                .shards(new ShardSystem(16))
                .tickBoxes(Map.of())
                .codeWords(Collections.emptySet())
                .staminaWhenUnwounded(9)
                .god("Ebron")
                .blessings(Collections.emptySet())
                .isResurrectionPossible(false)
                .resurrectionArrangement(new Resurrection("Resurrection Title",SectionId.book1(33))).build(),


                """
                        Liana prefers to make her home in mountain grottos
                        and woodland groves rather than in the squalid streets
                        of cities. She has the agility of a gazelle, the cunning of
                        a fox and the ferocity of an eagle. She has heard of a
                        City of Trees, deep within the forest of the Isle of
                        "Druids."""
        ); // Create a character with a non-outcast rank
        assertThatThrownBy(() -> testPlayerSession.validateInitialCreation(character.player())).isInstanceOfAny(FabledBusinessException.class); // Expect the validation error message
    }

}
