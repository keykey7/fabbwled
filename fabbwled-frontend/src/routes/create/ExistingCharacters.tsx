import { useEffect } from "react";
import { getAllCharacters } from "../../api/character.ts";

export default function ExistingCharacters() {
  const getAllCharactersTest = async () => {
    const characters = await getAllCharacters(1);
    console.log("CHARACTERS: ", characters);
  };

  useEffect(() => {
    console.log("existing characters");
    getAllCharactersTest();
  }, []);

  return <div>Here we will map the delivered existing characters</div>;
}
