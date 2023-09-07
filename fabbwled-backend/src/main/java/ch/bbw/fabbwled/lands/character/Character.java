package ch.bbw.fabbwled.lands.character;

import lombok.With;
import ch.bbw.fabbwled.lands.character.ProfessionEnum;
import ch.bbw.fabbwled.lands.character.RankEnum;
public class Character {
    @With
    public record CharacterDto(String name,
                               RankEnum rank
							ProfessionEnum profession,
                               int stamina,
                               int defence,
                               StatsDto fightingStats,
                               List<String> posessions,
                               String description) {
    }

    @With
    public record StatsDto(int charisma, int combat, int magic, int sanctity, int scouting, int thievery) {
    }
}
