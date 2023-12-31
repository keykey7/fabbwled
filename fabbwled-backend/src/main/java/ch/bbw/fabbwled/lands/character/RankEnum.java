package ch.bbw.fabbwled.lands.character;

import lombok.Getter;

@Getter
public enum RankEnum {
    OUTCAST(1),
    COMMONER(2),
    GUILDMEMBER(3),
    MASTER(4),
    GENTLEMAN(5),
    BARON(6),
    COUNT(7),
    EARL(8),
    MARQUIS(9),
    DUKE(10);

    private final int rankNumber;
    RankEnum(int rankNumber) {
        this.rankNumber = rankNumber;
    }

}