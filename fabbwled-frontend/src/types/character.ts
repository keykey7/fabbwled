interface CharacterCreateDto {
    player: PlayerDto;
    description: string;
}

interface PlayerDto {
    name: string;
    currentSection: [];         //new
    titlesAndHonours: string[];
    rank: number;               //new
    profession: string;         //new
    stamina: number;
    baseStats: BaseStatsDto;
    possessions: string[];
    shards: [];                 //new
    defence: number;
}

interface BaseStatsDto {
    charisma: number;
    combat: number;
    magic: number;
    sanctity: number;
    scouting: number;
    thievery: number;
}