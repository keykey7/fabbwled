import {useRef} from 'react';
import ReactDice, {ReactDiceRef} from "react-dice-complete";

type DiceComponentProps = {
    numberOfDice?: number;
    onRoll: (total: number) => void;
}

const DiceComponent = ({ numberOfDice = 1, onRoll }: DiceComponentProps) => {
    const reactDice = useRef<ReactDiceRef>(null)

    const rollDone = (totalValue: number) => {
        onRoll(totalValue);
    }

    const rollAll = () => {
        reactDice.current?.rollAll();
    }

    return (
        <div onClick={rollAll}>
            <ReactDice
                numDice={numberOfDice}
                ref={reactDice}
                rollDone={rollDone}
                dotColor={'#000000'}
                outline={true}
                outlineColor={'#000000'}
                faceColor={'#FFFFFF'}
            />
        </div>
    );
};

export default DiceComponent;
