package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.FabledTestBase;
import ch.bbw.fabbwled.lands.character.Character;
import ch.bbw.fabbwled.lands.character.ProfessionEnum;
import ch.bbw.fabbwled.lands.character.RankEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CharacterServiceTest extends FabledTestBase {

    @Autowired
    CharacterService characterService;

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
        Character.CharacterDto character = new Character.CharacterDto("Liana The Swift", RankEnum.OUTCAST, ProfessionEnum.WAYFARER, 9,
                new Character.StatsDto(2, 5, 2, 3, 6, 4), List.of("spear", "leather jerkin (Defence +1)", "map"),
                """
                        Liana prefers to make her home in mountain grottos
                        and woodland groves rather than in the squalid streets
                        of cities. She has the agility of a gazelle, the cunning of
                        a fox and the ferocity of an eagle. She has heard of a 
                        City of Trees, deep within the forest of the Isle of 
                        "Druids."""
        ); // Create a character with valid stats
        String result = characterService.validateCharacter(character);
        assertThat(result).isEmpty(); // Expect no validation errors
    }

    @Test
    void validateCharacterWithInvalidStats() {
        Character.CharacterDto character = new Character.CharacterDto("Liana The Swift", RankEnum.OUTCAST, ProfessionEnum.WAYFARER, 9,
                new Character.StatsDto(2, 5, 2, 3, 7, 4), List.of("spear", "leather jerkin (Defence +1)", "map"),
                """
                        Liana prefers to make her home in mountain grottos
                        and woodland groves rather than in the squalid streets
                        of cities. She has the agility of a gazelle, the cunning of
                        a fox and the ferocity of an eagle. She has heard of a 
                        City of Trees, deep within the forest of the Isle of 
                        "Druids."""
        ); // Create a character with valid stats; // Create a character with invalid stats
        String result = characterService.validateCharacter(character);
        assertThat(result).isEqualTo("Stats can only range between 1 and 6"); // Expect the validation error message
    }

    @Test
    void validateCharacterWithLargePossessionSize() {
        Character.CharacterDto character = new Character.CharacterDto("Liana The Swift", RankEnum.OUTCAST, ProfessionEnum.WAYFARER, 9,
                new Character.StatsDto(2, 5, 2, 3, 6, 4), List.of("spear", "leather jerkin (Defence +1)", "map", "sword", "shield", "sword", "shield", "sword", "shield", "sword", "shield", "sword", "shield"),
                """
                        Liana prefers to make her home in mountain grottos
                        and woodland groves rather than in the squalid streets
                        of cities. She has the agility of a gazelle, the cunning of
                        a fox and the ferocity of an eagle. She has heard of a 
                        City of Trees, deep within the forest of the Isle of 
                        "Druids."""
        ); // Create a character with valid stats; // Create a character with a large possession size
        String result = characterService.validateCharacter(character);
        assertThat(result).isEqualTo("Character possession size not allowed over 12"); // Expect the validation error message
    }

    @Test
    void validateCharacterWithNonOutcastRank() {
        Character.CharacterDto character = new Character.CharacterDto("Liana The Swift", RankEnum.COMMONER, ProfessionEnum.WAYFARER, 9,
                new Character.StatsDto(2, 5, 2, 3, 6, 4), List.of("spear", "leather jerkin (Defence +1)", "map"),
                """
                        Liana prefers to make her home in mountain grottos
                        and woodland groves rather than in the squalid streets
                        of cities. She has the agility of a gazelle, the cunning of
                        a fox and the ferocity of an eagle. She has heard of a 
                        City of Trees, deep within the forest of the Isle of 
                        "Druids."""
        ); // Create a character with a non-outcast rank
        String result = characterService.validateCharacter(character);
        assertThat(result).isEqualTo("Starting rank has to be 1"); // Expect the validation error message
    }

}
