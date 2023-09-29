package ch.bbw.fabbwled.lands.character;

import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.With;
public class Character {

    public record CharacterCreateDto(PlayerSession.PlayerDto player, String description){}

    @With
    public record BaseStatsDto(int charisma, int combat, int magic, int sanctity, int scouting, int thievery) {
    }
}
