import { useEffect, useState } from "react";
import CharacterCard from "../../../components/CharacterCard/CharacterCard.tsx";
import "./existing-characters.scss";
import {getAllCharacters} from "../../api/character.ts";
import { CharacterCreateDto } from "../../interfaces/character.ts";

export default function ExistingCharacters() {
  const [characters, setCharacters] = useState<CharacterCreateDto[]>([]);
  const BOOK_ID = 1; // The only book we implement
  const fetchCharacters = async () => {
    setCharacters(await getAllCharacters(BOOK_ID));
  };

  useEffect(() => {
    fetchCharacters();
  }, []);

  return (
    <div className="existing-characters-container">
      {characters.map((character, index) => {
        return (
          <CharacterCard
            character={character}
            imageUrl={`https://picsum.photos/200/20${index}`}
            key={index}
          />
        );
      })}
    </div>
  );
}
