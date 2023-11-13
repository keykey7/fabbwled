import { Route, Routes } from "react-router";
import AdventureSheet from "./routes/create/AdventureSheet.tsx";
import Game from "./routes/game/Game.tsx";
import ChooseCreate from "./routes/create/ChooseCreate.tsx";
import ExistingCharacters from "./routes/create/ExistingCharacters.tsx";
import "./app.scss"

function App() {
  return (
    <Routes>
      <Route path="/create" element={<ChooseCreate />} />
      <Route path="/create/character" element={<AdventureSheet />} />
      <Route
        path="/create/existing-character"
        element={<ExistingCharacters />}
      />
      <Route path="/game" element={<Game />} />
    </Routes>
  );
}

export default App;
