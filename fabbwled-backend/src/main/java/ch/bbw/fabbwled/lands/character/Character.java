package ch.bbw.fabbwled.lands.character;

import lombok.With;
public class Character {

    public record CharacterCreateDto(PlayerDto player, String description){}

    @With
    public record BaseStatsDto(int charisma, int combat, int magic, int sanctity, int scouting, int thievery) {
        public static BaseStatsDto all1() {
            return new BaseStatsDto(1, 1, 1, 1, 1, 1);
        }

        public BaseStatsDto withCombatAdd(int amount) {
            return withCombat(Math.max(combat + amount, 1));
        }

        public int getByType(AbilityEnum type) {
            return switch (type) {
                case MAGIC -> magic;
                case CHARISMA -> charisma;
                case COMBAT -> combat;
                case SANCTITY -> sanctity;
                case SCOUTING -> scouting;
                case THIEVERY -> thievery;
            };
        }
    }
}
