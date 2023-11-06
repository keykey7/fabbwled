import { useEffect, useState } from "react";
import { getAllCharacters } from "../../api/character.ts";
import CharacterCard from "../../components/CharacterCard/CharacterCard.tsx";
import "./existing-characters.scss";

export default function ExistingCharacters() {
  const [characters, setCharacters] = useState<CharacterCreateDto[]>([]);
  const BOOK_ID = 1; // The only book we implement
  const getAllCharactersTest = async () => {
    setCharacters(await getAllCharacters(BOOK_ID));
  };

  useEffect(() => {
    getAllCharactersTest();
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
