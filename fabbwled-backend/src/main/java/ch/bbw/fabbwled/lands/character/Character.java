package ch.bbw.fabbwled.lands.character;

import lombok.With;
public class Character {

    public record CharacterCreateDto(PlayerDto player, String description){}

    @With
    public record BaseStatsDto(int charisma, int combat, int magic, int sanctity, int scouting, int thievery) {
        public static BaseStatsDto all1() {
            return new BaseStatsDto(1, 1, 1, 1, 1, 1);
        }
    }
}
