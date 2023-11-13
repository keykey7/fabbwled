import "./choose-create.scss";
import { Link } from "react-router-dom";

export default function ChooseCreate() {
  return (
    <div className="choose_create_container">
      <Link to={"/create/character"}>
        <button className="choose_create_button">Create own character</button>
      </Link>{" "}
      <Link to={"/create/existing-character"}>
        <button className="choose_create_button">
          Play with existing character
        </button>
      </Link>
    </div>
  );
}
