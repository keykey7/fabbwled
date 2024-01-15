package ch.bbw.fabbwled.lands.character;

import lombok.With;

@With
public record Enemy(String name, int combat, int defence, int stamina) {
}
