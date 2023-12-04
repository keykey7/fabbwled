const baseApiURL = "http://localhost:8080/api/player";
export async function setCharacter(data: CharacterCreateDto): Promise<any> {
  try {
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
  } catch (error: any) {
    // deliberately used any here
    throw new Error(`An error occurred: ${error.message}`); // Handle network errors
  }
}

export async function getAllCharacters(
  bookId: number,
): Promise<CharacterCreateDto[]> {
  try {
    const response = await fetch(`${baseApiURL}/${bookId}/all`, {
      method: "GET",
    });

    if (response.ok) {
      return await response.json();
    } else {
      throw new Error(`API request failed with status: ${response.status}`);
    }
  } catch (error: any) {
    // Handle any network or other errors here
    throw new Error(`An error occurred: ${error.message}`);
  }
}
