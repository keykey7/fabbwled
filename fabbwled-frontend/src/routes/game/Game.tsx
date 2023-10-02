import styles from "./Game.module.scss";
import { Button } from "@mui/material";

export default function Game() {
  return (
    <div className={styles.desk}>
      <div className={styles.book}>
        <div className={styles.page}>
          <p className={styles.pageIndicator}>1</p>
          <p className={styles.pageText}>
            The chest springs open with a click. Inside you find 60 Shards,
            amandolin (CHARISMA +1), and a potion of healing. Thepotion can be
            used once, at any time (even in combat) to restore5 Stamina points.
            There is also an ancient religious text aboutthe gods of the Uttaku,
            called the scroll of Ebron, whichreveals that one of the gods of the
            Uttakin is called Ebron, andthat he has fourteen angles.Note
            whatever you are taking on your Adventure Sheet, andturn to 10.
          </p>
        </div>
        <div className={styles.page}>
          <div className={styles.statsDisplay}>
            <h3>Xisaj Nikolaj</h3>
            <div className={styles.stats}>
              {/* TODO: Replace this with table */}
              <ul>
                <li>Rank: 1</li>
                <li>Profession: Hurensohn</li>
                <li>Stamina: -3</li>
                <li>Defence: was das</li>
                <li>Money: Dark souls</li>
              </ul>
              <ul>
                <li>Charisma: 1</li>
                <li>Combo: 1</li>
                <li>Magic: 2</li>
                <li>Sanctity: 5</li>
                <li>Scouting: 1</li>
                <li>Thievery: 1</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div className={styles.side}>
        <div className={styles.buttons}>
          <Button className={styles.eraser}>Exit</Button>
        </div>
      </div>
    </div>
  );
}
