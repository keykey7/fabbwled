package ch.bbw.fabbwled.lands.service;


import ch.bbw.fabbwled.lands.character.Character;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.character.ProfessionEnum;
import ch.bbw.fabbwled.lands.character.RankEnum;
import ch.bbw.fabbwled.lands.exception.FabledBusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to create new characters from scratch and provide pre-defined ones.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CharacterService {

    public void validateInitialCreation(PlayerDto playerDto) {
        Character.BaseStatsDto stats = playerDto.baseStats();
        int charisma = stats.charisma();
        int combat = stats.combat();
        int magic = stats.magic();
        int sanctity = stats.sanctity();
        int scouting = stats.scouting();
        int thievery = stats.thievery();
        if (charisma < 1 || charisma > 6
                || combat < 1 || combat > 6
                || magic < 1 || magic > 6
                || sanctity < 1 || sanctity > 6
                || scouting < 1 || scouting > 6
                || thievery < 1 || thievery > 6) {
            throw new FabledBusinessException("Stats can only range between 1 and 6");
        }
        if (!playerDto.rank().equals(RankEnum.OUTCAST)) {
            throw new FabledBusinessException("char must start as outcast");
        }
    }

    public List<Character.CharacterCreateDto> getAllCharacters(int bookId) {
        if (bookId == 1) {
            return List.of(new Character.CharacterCreateDto(PlayerDto.builder()
                            .name("Liana The Swift")
                            .profession(ProfessionEnum.WAYFARER)
                            .baseStats(new Character.BaseStatsDto(2, 5, 2, 3, 6, 4))
                            .build(),                                        
                            """
                                    Liana prefers to make her home in mountain grottos
                                    and woodland groves rather than in the squalid streets
                                    of cities. She has the agility of a gazelle, the cunning of
                                    a fox and the ferocity of an eagle. She has heard of a
                                    City of Trees, deep within the forest of the Isle of
                                    "Druids."""
                    ),
                    new Character.CharacterCreateDto(PlayerDto.builder()
                            .name("Andriel The Hammer")
                            .profession(ProfessionEnum.WARRIOR)
                            .baseStats(new Character.BaseStatsDto(3, 6, 2, 4, 3, 2))
                            .possessions(List.of("battle-axe", "leather jerkin (Defence +1)", "map"))
                            .build(),
                            """
                                    Andriel seeks fame through adventure and the glory of battle.
                                    He left his homeland when an extended outbreak of peace
                                    made his skills redundant there. He is blunt and outspoken,
                                    but scrupulously follows the warrior’s code. He knows the
                                    merchants’ guild in Yellowport needs assistance."""),
                    new Character.CharacterCreateDto(PlayerDto.builder()
                            .name("Chalor The Exiled One")
                            .profession(ProfessionEnum.MAGE)
                            .baseStats(new Character.BaseStatsDto(2, 2, 6, 1, 5, 3))
                            .possessions(List.of("staff, leather jerkin (Defence +1), map"))
                            .build(),
                            """
                                    Chalor is an outcast by choice, shunning his native land
                                    and the family who spurned him, driven by a burning
                                    desire for secret knowledge. His goal is to become one
                                    of the mightiest wizards of the world, and nothing will
                                    stand in his way. For now, he is looking for the Gold
                                    Dust Tavern in Yellowport, where adventure awaits."""),
                    new Character.CharacterCreateDto(PlayerDto.builder()
                            .name("Marana Fireheart")
                            .profession(ProfessionEnum.ROGUE)
                            .baseStats(new Character.BaseStatsDto(5, 4, 4, 1, 2, 6))
                            .possessions(List.of("sword", "leather jerkin (Defence +1)", "map"))
                            .build(),

                            """
                                    Marana is a fiercely independent woman who grew up in the
                                    back streets of her home town. Forced to flee because she
                                    was too active in her chosen profession, she has come to new
                                    lands to seek her fortune. Devious and resourceful, she can
                                    break in almost anywhere. She has heard that the temple of
                                    Sig in Marlock City needs the service of a rogue."""),
                    new Character.CharacterCreateDto(PlayerDto.builder()
                            .name("Ignatius The Devout")
                            .profession(ProfessionEnum.PRIEST)
                            .baseStats(new Character.BaseStatsDto(4, 2, 3, 6, 4, 2))
                            .possessions(List.of("mace", "leather jerkin (Defence +1)", "map"))
                            .build(),
                            """
                                    Ignatius is a traveller whose desire is to learn all he can
                                    about the deities of the Fabled Lands. His strong beliefs
                                    give his sermons added zest, and he has enthralled many
                                    a crowd with his impassioned speeches. He is looking for
                                    the House of Priests in Marlock City"""),
                    new Character.CharacterCreateDto(PlayerDto.builder()
                            .name("Astariel Skysong")
                            .profession(ProfessionEnum.TROUBADOUR)
                            .baseStats(new Character.BaseStatsDto(6, 3, 4, 3, 2, 4))
                            .possessions(List.of("sword", "leather jerkin (Defence +1)", "map"))
                            .build(),
                            """
                                    Astariel has the wanderlust, and chafes if he has to remain
                                    in one place for any length of time. He enjoys the
                                    freedom of the open road and the thought that he never
                                    knows what adventures each new day will bring. He
                                    lives by his wits and is a familiar figure at tavern firesides,
                                    where he regales travellers with his tales."""
                    ));
        }
        return List.of();
    }
}
