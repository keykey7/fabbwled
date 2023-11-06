interface CharacterCreateDto {
  player: Player;
  description: string;
}

interface Player {
  name: string;
  currentSection: Section;
  titlesAndHonours: string[];
  rank:
    | "OUTCAST"
    | "COMMONER"
    | "GUILDMEMBER"
    | "MASTER"
    | "GENTLEMAN"
    | "BARON"
    | "COUNT"
    | "EARL"
    | "MARQUIS"
    | "DUKE";
  profession:
    | "Wayfarer"
    | "Warrior"
    | "Mage"
    | "Rogue"
    | "Priest"
    | "Troubadour";
  stamina: number;
  baseStats: BaseStats;
  possessions: string[];
  shards: Shards;
  tickBoxes: TickBoxes;
  codeWords: string[];
  defence: number;
}

interface BaseStats {
  charisma: number;
  combat: number;
  magic: number;
  sanctity: number;
  scouting: number;
  thievery: number;
}

interface Section {
  bookId: number;
  sectionId: number;
}

interface Shards {
  shardCount: number;
}

interface TickBoxes {
  additionalProp1: number;
  additionalProp2: number;
  additionalProp3: number;
}