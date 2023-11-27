import styles from "./Game.module.scss";
import {useEffect, useState} from "react";
import DiceComponent from "../../DiceComponent.tsx";
import {Link} from "react-router-dom";
import {convertToElement} from "../../SectionRenderer.tsx";
import {Section, Player} from '../../interfaces/character.ts'

export default function Game() {
    const [character, setCharacter] = useState<Player>();
    const [section, setSection] = useState<Section>();

    useEffect(() => {
        getCharacter();
    }, []);

    useEffect(() => {
        getSection();
    }, [character?.currentSection.sectionId]);

    const getCharacter = async () => {
        const response = await fetch('http://localhost:8080/api/player')
        const data = await response.json();
        setCharacter(data)
    }

    function rollDice(diceNumber: number) {
        console.log(diceNumber)
    }

    const getSection = async () => {
        const response = await fetch(`http://localhost:8080/api/section/1/15`)//${character?.currentSection.sectionId}
        const data = await response.json();
        console.log(data)
        setSection(data)
    }

    return (
      <div className={styles.desk}>
        <div className={styles.bookWrapper}>
          <div className={styles.book}>
            <div className={styles.page}>
              <p className={styles.pageIndicator}>1</p>
              <p className={styles.pageText}>
                {section && convertToElement(section.body, () => {})}
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
