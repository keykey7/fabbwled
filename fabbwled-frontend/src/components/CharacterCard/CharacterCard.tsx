import "./character-card.scss";

export default function CharacterCard() {
  return (
    <div className="character-card">
      <div className="character-header">
        <div className="character-name">
          <h2>Name</h2>
        </div>
        <div className="character-image">
          <img src="https://picsum.photos/200/200?grayscale" alt="Character" />
        </div>
      </div>
      <div className="character-info">
        <h3>General Information</h3>
        <div className="attribute">Rank</div>
        <div className="attribute">Profession</div>
        <div className="attribute">Stamina</div>
        <div className="attribute">Defence</div>
        <div className="attribute">Money</div>
        <h3>Abilities</h3>
        <div className="attribute">Charisma</div>
        <div className="attribute">Combat</div>
        <div className="attribute">Magic</div>
        <div className="attribute">Sanctity</div>
        <div className="attribute">Scouting</div>
        <div className="attribute">Thievery</div>
        <div className="attribute">Possessions</div>
        <div className="description">Description</div>
      </div>
    </div>
  );
}
