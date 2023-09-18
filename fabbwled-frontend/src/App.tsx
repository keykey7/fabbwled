import { Route, Routes } from "react-router";
import Create from "./routes/create/Create.tsx";
import Game from "./routes/game/Game.tsx";

function App() {
  return (
    <Routes>
      <Route path="/create" element={<Create />} />
      <Route path="/game" element={<Game />} />
    </Routes>
  );
}

export default App;
