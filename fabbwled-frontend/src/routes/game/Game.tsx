import styles from "./Game.module.scss";
import { useEffect, useState } from "react";
import DiceComponent from "../../DiceComponent.tsx";
import { Link } from "react-router-dom";
import { convertToElement } from "../../SectionRenderer.tsx";
import { Section, Player } from "../../interfaces/character.ts";

export default function Game() {
  const [character, setCharacter] = useState<Player>();
  const [section, setSection] = useState<Section>();

  useEffect(() => {
    getCharacter();
    getSection("1").then(setSection);
  }, []);

  const getCharacter = async () => {
    const response = await fetch("http://localhost:8080/api/player");
    const data = await response.json();
    setCharacter(data);
  };

  function rollDice(diceNumber: number) {
    console.log(diceNumber);
  }

  const getSection = async (section: string): Promise<Section> => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/section/1/${section}`,
      ); //${character?.currentSection.sectionId}
      const data = await response.json();
      console.log(data);
      return data;
    } catch (e) {
      return {
        body: {
          type: "CONTAINER",
          style: "NONE",
          children: [
            {
              type: "SIMPLE",
              text: "Haha existiert nicht",
              style: "NONE",
            },
          ],
        },
        id: "0-0",
        ticks: { ticked: 0, total: 0 },
      };
    }
  };

  return (
    <div className={styles.desk}>
      <div className={styles.bookWrapper}>
        <div className={styles.book}>
          <div className={styles.page}>
            <p className={styles.pageIndicator}>
              {section?.id && section.id.split("-")[1]}
            </p>
            <p className={styles.pageText}>
              {section && convertToElement(section.body, () => {}, getSection)}
            </p>
          </div>
          <div className={styles.page}>
            <div className={styles.statsDisplay}>
              <h3>{character?.name}</h3>
              <div className={styles.stats}>
                <ul>
                  <li>Rank: {character?.rank}</li>
                  <li>Profession: {character?.profession}</li>
                  <li>Stamina: {character?.stamina}</li>
                  <li>Defence: {character?.defence}</li>
                  <li>Money: {character?.shards?.shardCount}</li>
                </ul>
                <ul>
                  <li>Charisma: {character?.baseStats?.charisma}</li>
                  <li>Combo: {character?.baseStats?.combat}</li>
                  <li>Magic: {character?.baseStats?.magic}</li>
                  <li>Sanctity: {character?.baseStats?.sanctity}</li>
                  <li>Scouting: {character?.baseStats?.scouting}</li>
                  <li>Thievery: {character?.baseStats?.thievery}</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className={styles.side}>
        <div className={styles.dices}>
          <DiceComponent
            numberOfDice={2}
            onRoll={(diceNumber) => rollDice(diceNumber)}
          ></DiceComponent>
        </div>
        <div className={styles.buttons}>
          <Link to={"/create"}>
            <button className={styles.pencil} title={"New Game"}>
              <img src={"/pencil.png"} />
            </button>
          </Link>
          <button
            className={styles.eraser}
            title={"Exit the current game"}
            onClick={() => window.close()}
          >
            <img src={"/eraser.png"} />
          </button>
        </div>
      </div>
    </div>
  );
}
