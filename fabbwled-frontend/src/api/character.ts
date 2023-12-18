import { CharacterCreateDto } from "../interfaces/character.ts";

const baseApiURL = "http://localhost:8080/api/player";

export async function setCharacter(data: CharacterCreateDto): Promise<unknown> {
  const response = await fetch(baseApiURL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  if (response.ok) {
    return await response.json();
  } else {
    throw new Error(`API request failed with status: ${response.status}`);
  }
}

export async function getAllCharacters(
  bookId: number,
): Promise<CharacterCreateDto[]> {
  const response = await fetch(`${baseApiURL}/${bookId}/all`, {
    method: "GET",
  });

  if (response.ok) {
    return await response.json();
  } else {
    throw new Error(`API request failed with status: ${response.status}`);
  }
}
