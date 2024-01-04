import { Container } from "../SectionRenderer.tsx";

export interface CharacterCreateDto {
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
  profession: Profession;
  stamina: number;
  staminaWhenUnwounded: number;
  baseStats: BaseStats;
  possessions: string[];
  shards: number;
  codeWords: string[];
  defence: number;
  blessings: string[];
  tickBoxes: {};
  poisons: string[];
  disease: string[];
  curses: string[];
  persistentSectionStore: {};
  mostRecentDiceRoll: number[];
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
