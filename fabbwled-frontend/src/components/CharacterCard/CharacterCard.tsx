import "./character-card.scss";
import { setCharacter } from "../../api/character.ts";

type CharacterCardProps = {
  character: CharacterCreateDto;
  imageUrl: string;
};

export default function CharacterCard(props: CharacterCardProps) {
  const { character } = props;
  const player = props.character.player;
  const baseStats = player.baseStats;

  return (
    <div className="character-card">
      <div className="character-header">
        <div className="character-name">
          <h3>{player.name}</h3>
        </div>
        <div className="character-image">
          <img src={props.imageUrl} alt="Character" />
        </div>
      </div>
      <div className="character-info">
        <h3>General Information</h3>
        <div className="attribute">Rank: {player.rank}</div>
        <div className="attribute">Profession: {player.profession}</div>
        <div className="attribute">Stamina: {player.stamina}</div>
        <div className="attribute">Defence: {player.defence}</div>
        <div className="attribute">Shards: {player.shards.shardCount}</div>
        <h3>Abilities</h3>
        <div className="attribute">Charisma: {baseStats.charisma}</div>
        <div className="attribute">Combat: {baseStats.combat}</div>
        <div className="attribute">Magic: {baseStats.magic}</div>
        <div className="attribute">Sanctity: {baseStats.sanctity}</div>
        <div className="attribute">Scouting: {baseStats.scouting}</div>
        <div className="attribute">Thievery: {baseStats.thievery}</div>
        <div className="attribute">Possessions: {player.possessions}</div>
        <div className="description">{character.description}</div>
      </div>
      <div>
        <button
          className="character-select-button"
          onClick={() => setCharacter(character)}
        >
          Select
        </button>
      </div>
    </div>
  );
}
