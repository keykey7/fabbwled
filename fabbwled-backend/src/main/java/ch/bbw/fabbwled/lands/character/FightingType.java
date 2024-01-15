package ch.bbw.fabbwled.lands.character;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FightingType {
    TO_THE_DEATH("toDeath"),
    DUEL("duel");

    private final String label;
}
