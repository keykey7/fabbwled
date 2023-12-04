package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.FabledTestBase;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.character.RankEnum;
import ch.bbw.fabbwled.lands.exception.FabledBusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CharacterServiceTest extends FabledTestBase {

    @Autowired
    CharacterService characterService;

    @Autowired
    PlayerSession testPlayerSession;


    @Test
    void byWrongBookId() {
        assertThat(characterService.getAllCharacters(2)).isEqualTo(List.of());
    }

    @Test
    void byCorrectBookId() {
        assertThat(characterService.getAllCharacters(1)).hasSize(6);
    }

    @Test
    void validateCharacterWithValidStats() {
        characterService.validateInitialCreation(PlayerDto.empty());
        characterService.getAllCharacters(1).forEach(player -> characterService.validateInitialCreation(player.player()));
    }

    @Test
    void validateCharacterWithInvalidStats() {
        assertThatThrownBy(() ->  characterService.validateInitialCreation(PlayerDto.empty().withStats(x -> x.withScouting(7))))
                .isInstanceOfAny(FabledBusinessException.class); // Expect the validation error message
    }

    @Test
    void validateCharacterWithNonOutcastRank() {
        assertThatThrownBy(() ->  characterService.validateInitialCreation(PlayerDto.empty().withRank(RankEnum.EARL)))
                .isInstanceOfAny(FabledBusinessException.class); // Expect the validation error message
    }

}
