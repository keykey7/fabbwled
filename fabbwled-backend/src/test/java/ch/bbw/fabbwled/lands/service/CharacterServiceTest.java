package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.FabledTestBase;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.character.Character;
import ch.bbw.fabbwled.lands.character.ProfessionEnum;
import ch.bbw.fabbwled.lands.character.RankEnum;
import ch.bbw.fabbwled.lands.exception.FabledBusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

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
        assertThat(characterService.getAllCharacters(id)).hasSizeGreaterThan(5);
    }

    @Test
    void validateCharacterWithValidStats() {
        Character.CharacterCreateDto character = new Character.CharacterCreateDto(new PlayerSession.PlayerDto("Liana The Swift", SectionId.book1(15), Collections.emptySet(),RankEnum.OUTCAST, ProfessionEnum.WAYFARER, 9,
                new Character.BaseStatsDto(2, 5, 2, 3, 6, 4), List.of("spear", "leather jerkin (Defence +1)", "map")),
                """
                        Liana prefers to make her home in mountain grottos
                        and woodland groves rather than in the squalid streets
                        of cities. She has the agility of a gazelle, the cunning of
                        a fox and the ferocity of an eagle. She has heard of a
                        City of Trees, deep within the forest of the Isle of
                        "Druids.""")
                ; // Create a character with valid stats
        testPlayerSession.validatePlayer(character.player());
    }

    @Test
    void validateCharacterWithInvalidStats() {
        Character.CharacterCreateDto character = new Character.CharacterCreateDto(new PlayerSession.PlayerDto("Liana The Swift", SectionId.book1(15),Collections.emptySet(),RankEnum.OUTCAST, ProfessionEnum.WAYFARER, 9,
                new Character.BaseStatsDto(2, 5, 2, 3, 7, 4), List.of("spear", "leather jerkin (Defence +1)", "map")),
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
        Character.CharacterCreateDto character = new Character.CharacterCreateDto(new PlayerSession.PlayerDto("Liana The Swift",SectionId.book1(15),Collections.emptySet(), RankEnum.OUTCAST, ProfessionEnum.WAYFARER, 9,
                new Character.BaseStatsDto(2, 5, 2, 3, 6, 4), List.of("spear", "leather jerkin (Defence +1)", "map", "sword", "shield", "sword", "shield", "sword", "shield", "sword", "shield", "sword", "shield")),
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
        Character.CharacterCreateDto character = new Character.CharacterCreateDto(new PlayerSession.PlayerDto("Liana The Swift", SectionId.book1(15),Collections.emptySet(),RankEnum.COMMONER, ProfessionEnum.WAYFARER, 9,
                new Character.BaseStatsDto(2, 5, 2, 3, 6, 4), List.of("spear", "leather jerkin (Defence +1)", "map")),
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
