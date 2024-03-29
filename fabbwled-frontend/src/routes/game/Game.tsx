import styles from "./Game.module.scss";
import { useEffect, useState } from "react";
import DiceComponent from "../../DiceComponent.tsx";
import { convertToElement } from "../../SectionRenderer.tsx";
import { Player, Section } from "../../interfaces/character.ts";
import { Link } from "react-router-dom";
import Inventory from "../../components/Inventory";

export default function Game() {
  const [character, setCharacter] = useState<Player>();
  const [section, setSection] = useState<Section>();

  useEffect(() => {
    getCharacter().then((player: Player) => {
      setCharacter(player);
      getSection(player.currentSection.split("-")[1]).then(setSection);
    });
  }, []);

  const getCharacter = async () => {
    const response = await fetch("http://localhost:8080/api/player");
    return response.json();
  };

  function rollDice(diceNumber: number) {
    console.log(diceNumber);
  }

  function exitGame() {
    document.cookie = "";
    window.location.href = "about:blank";
  }

  const clickItem = (clickId: number) => {
    return fetch(`http://localhost:8080/api/section/click`, {
      method: "POST",
      body: JSON.stringify({
        clickId: clickId,
      }),
      headers: {
        "Content-Type": "application/json",
      },
    }).then((res) => res.json());
  };

  const getSection = async (sectionId: string): Promise<Section> => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/section/1/${sectionId}`,
      ); //${character?.currentSection.sectionId}
      const data = await response.json();
      return data;
    } catch (e) {
      return {
        body: {
          type: "CONTAINER",
          style: "NONE",
          children: [
            {
              type: "SIMPLE",
              text: `No Section for sectionId: ${sectionId} found`,
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
              {section?.id ? section.id.split("-")[1] : ""}
            </p>
            <p className={styles.pageText}>
              {section?.body
                ? convertToElement(section.body, (id) =>
                    clickItem(id).then(setSection),
                  )
                : "Section not implemented"}
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
                  <li>Money: {character?.shards}</li>
                </ul>
                <ul>
                  <li>Charisma: {character?.baseStats?.charisma}</li>
                  <li>Combat: {character?.baseStats?.combat}</li>
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
          <Link to={`/create`}>
            <button
              className={styles.pencil}
              title={"New Game"}
              onClick={() => {
                document.cookie = "";
              }}
            >
              <img src={"/pencil.png"} />
            </button>
          </Link>
          <button
            className={styles.eraser}
            title={"Exit the current game"}
            onClick={exitGame}
          >
            <img src={"/eraser.png"} />
          </button>
          <Inventory onAction={() => console.log("action")} />
        </div>
      </div>
    </div>
  );
}
