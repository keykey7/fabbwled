package ch.bbw.fabbwled.lands.character;

import lombok.With;
import java.util.List;
public class Character {
    @With
    public record CharacterDto(String name,
                               RankEnum rank,
							   ProfessionEnum profession,
                               int stamina,
                               StatsDto baseStats,
                               List<String> possessions,
                               String description) {
    }

    @With
    public record StatsDto(int charisma, int combat, int magic, int sanctity, int scouting, int thievery) {
    }
}
