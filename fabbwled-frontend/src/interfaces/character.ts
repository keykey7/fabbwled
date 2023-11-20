import {Container} from "../SectionRenderer.tsx";

interface CharacterCreateDto {
    player: Player;
    description: string;
}

export interface Player {
    name: string;
    currentSection: `1-${number}`;
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
    staminaWhenUnwounded: number;
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

export interface Section {
  id: string;
  body: Container;
  ticks: {
    total: number;
    ticked: number;
  };
}

interface Shards {
  shardCount: number;
}

interface SectionId {
  sectionId?: number;
  bookId?: number;
}
