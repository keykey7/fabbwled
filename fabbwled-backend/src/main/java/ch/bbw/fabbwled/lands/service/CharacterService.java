package ch.bbw.fabbwled.lands.service;


import ch.bbw.fabbwled.lands.character.Character;
import ch.bbw.fabbwled.lands.character.ProfessionEnum;
import ch.bbw.fabbwled.lands.character.RankEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CharacterService {

    public List<Character.CharacterDto> getAllCharacters(int bookId) {
        if(bookId == 1) {
            return List.of(new Character.CharacterDto("Liana The Swift", RankEnum.OUTCAST, ProfessionEnum.WAYFARER, 9, 8,
                            new Character.StatsDto(2, 5, 2, 3, 6, 4), List.of("spear", "leather jerkin (Defence +1)", "map"),
                            """
                                    Liana prefers to make her home in mountain grottos
                                    and woodland groves rather than in the squalid streets
                                    of cities. She has the agility of a gazelle, the cunning of
                                    a fox and the ferocity of an eagle. She has heard of a 
                                    City of Trees, deep within the forest of the Isle of 
                                    "Druids."""
                    ),
                    new Character.CharacterDto("Andriel The Hammer", RankEnum.OUTCAST, ProfessionEnum.WARRIOR, 9, 9,
                            new Character.StatsDto(3, 6, 2, 4, 3, 2),
                            List.of("battle-axe", "leather jerkin (Defence +1)", "map"),
                            """
                                    Andriel seeks fame through adventure and the glory of battle.
                                    He left his homeland when an extended outbreak of peace
                                    made his skills redundant there. He is blunt and outspoken, 
                                    but scrupulously follows the warrior’s code. He knows the
                                    merchants’ guild in Yellowport needs assistance."""
                    ,
                    new Character.CharacterDto("Chalor The Exiled One", RankEnum.OUTCAST, ProfesionEnum.Mage, 9, 5,
                            new Character.StatsDto(2, 2, 6, 1, 5, 3),
                            List.of("staff, leather jerkin (Defence +1), map"),
                            """
                                    Chalor is an outcast by choice, shunning his native land
                                    and the family who spurned him, driven by a burning 
                                    desire for secret knowledge. His goal is to become one
                                    of the mightiest wizards of the world, and nothing will 
                                    stand in his way. For now, he is looking for the Gold
                                    Dust Tavern in Yellowport, where adventure awaits.""",
                    new Character.CharacterDto("Marana Fireheart", RankEnum.OUTCAST, ProfessionEnum.ROGUE, 9, 7,
                            new Character.StatsDto(5, 4, 4, 1, 2, 6),
                            List.of("sword", "leather jerkin (Defence +1)", "map"),
                            """
                                    Marana is a fiercely independent woman who grew up in the
                                    back streets of her home town. Forced to flee because she
                                    was too active in her chosen profession, she has come to new
                                    lands to seek her fortune. Devious and resourceful, she can
                                    break in almost anywhere. She has heard that the temple of
                                    Sig in Marlock City needs the service of a rogue.""",
                    new Character.CharacterDto("Ignatius The Devout", RankEnum.OUTCAST, ProfessionEnu.PRIEST, 9, 5,
                            new Character.StatsDto(4, 2, 3, 6, 4, 2),
                            List.of("mace", "leather jerkin (Defence +1)", "map"),
                            """
                                    Ignatius is a traveller whose desire is to learn all he can
                                    about the deities of the Fabled Lands. His strong beliefs
                                    give his sermons added zest, and he has enthralled many
                                    a crowd with his impassioned speeches. He is looking for
                                    the House of Priests in Marlock City""",
                    new Character.CharacterDto("Astariel Skysong",RankEnum.OUTCAST,9,6,
                            new Character.StatsDto(6,3,4,3,2,4),
                            List.of( "sword", "leather jerkin (Defence +1)", "map"),
                            """
                                    Astariel has the wanderlust, and chafes if he has to remain
                                    in one place for any length of time. He enjoys the
                                    freedom of the open road and the thought that he never
                                    knows what adventures each new day will bring. He
                                    lives by his wits and is a familiar figure at tavern firesides,
                                    where he regales travellers with his tales."""
            );
        }
        return List.of();
    }
}
