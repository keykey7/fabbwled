const apiUrl = "http://localhost:8080/api/player";

export async function createCharacter(data: CharacterCreateDto): Promise<any> {
  try {
    const response = await fetch(apiUrl + "/setCharacter", {
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
    // Handle any network or other errors here
    throw new Error(`An error occurred: ${error.message}`);
  }
}

export async function getAllCharacters(bookId: number): Promise<any> {
  try {
    const response = await fetch(`${apiUrl}/${bookId}/all`, {
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

/*
{
    "player": {
    "name": "string",
        "currentSection": {
        "bookId": 0,
            "sectionId": 0
    },
    "titlesAndHonours": [
        "string"
    ],
        "rank": "OUTCAST",
        "profession": "Wayfarer",
        "stamina": 0,
        "baseStats": {
        "charisma": 0,
            "combat": 0,
            "magic": 0,
            "sanctity": 0,
            "scouting": 0,
            "thievery": 0
    },
    "possessions": [
        "string"
    ],
        "shards": {
        "shardCount": 0
    },
    "defence": 0
},
    "description": "string"
}*/
