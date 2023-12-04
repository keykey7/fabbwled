import "./character-card.scss";
import { setCharacter } from "../../api/character.ts";
import { CharacterCreateDto } from "../../interfaces/character.ts";
import {Link} from "react-router-dom";

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
        <p className="attribute">Rank: {player.rank}</p>
        <p className="attribute">Profession: {player.profession}</p>
        <p className="attribute">Stamina: {player.stamina}</p>
        <p className="attribute">Defence: {player.defence}</p>
        <p className="attribute">Shards: {player.shards.shardCount}</p>
        <h3>Abilities</h3>
        <p className="attribute">Charisma: {baseStats.charisma}</p>
        <p className="attribute">Combat: {baseStats.combat}</p>
        <p className="attribute">Magic: {baseStats.magic}</p>
        <p className="attribute">Sanctity: {baseStats.sanctity}</p>
        <p className="attribute">Scouting: {baseStats.scouting}</p>
        <p className="attribute">Thievery: {baseStats.thievery}</p>
        <p className="attribute">Possessions: {player.possessions}</p>
        <p className="description">{character.description}</p>
      </div>
      <div>
        <Link to={'/game'}>
        <button
          className="character-select-button"
          onClick={() => setCharacter(character)}
        >
          Select
        </button>
        </Link>
      </div>
    </div>
  );
}
