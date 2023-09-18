import styles from "./Game.module.scss";

export default function Game() {
    return (<div className={styles.desk}>
        <div className={styles.book}>
            <div className={styles.page}>
                <p className={styles.pageIndicator}>1</p>
                <p className={styles.pageText}>The chest springs open with a click. Inside you find 60 Shards, amandolin
                    (CHARISMA +1), and a potion
                    of healing. Thepotion can be used once, at any time (even in combat) to restore5 Stamina points.
                    There is also an ancient religious text aboutthe gods of the Uttaku, called the scroll of Ebron,
                    whichreveals that one of the gods of the Uttakin is called Ebron, andthat he has fourteen
                    angles.Note whatever you are taking on your Adventure Sheet, andturn to 10.</p>
            </div>
            <div className={styles.page}>
                Stats and stuff
            </div>
        </div>
    </div>);
}
