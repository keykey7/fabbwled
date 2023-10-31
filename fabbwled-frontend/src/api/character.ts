const baseApiURL = "http://localhost:8080/api/player";
export async function createCharacter(data: CharacterCreateDto): Promise<any> {
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
