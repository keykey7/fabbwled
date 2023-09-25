import { Route, Routes } from "react-router";
import AdventureSheet from "./routes/create/AdventureSheet.tsx";
import Game from "./routes/game/Game.tsx";

function App() {
  return (
    <Routes>
      <Route path="/create" element={<AdventureSheet />} />
      <Route path="/game" element={<Game />} />
    </Routes>
  );
}

export default App;
