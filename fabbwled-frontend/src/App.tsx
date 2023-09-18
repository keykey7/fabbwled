import { Route, Routes } from "react-router";
import Game from "./routes/game/Game.tsx";
import ChooseCreate from "./routes/create/ChooseCreate.tsx";
import './app.scss'

function App() {
  return (
    <Routes>
      <Route path="/create" element={<ChooseCreate />} />
      <Route path="/game" element={<Game />} />
    </Routes>
  );
}

export default App;
