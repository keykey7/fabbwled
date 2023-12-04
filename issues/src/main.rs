use serde_yaml::Value;

fn main() {
    let content = std::fs::read_to_string("fabled.txt").unwrap();
    let mut content = &content.as_str()[2..];

    let mut sections = Vec::new();

    for id in 2..=680 {
        if (146..150).contains(&id)
            || (223..227).contains(&id)
            || id == 429
            || (434..439).contains(&id)
            || id == 461
            || (526..529).contains(&id)
            || (530..532).contains(&id)
            || id == 665
        {
            continue;
        }
        let next = format!("\n{id}\n");

        dbg!(id, &content.get(..52));
        let (section, rest) = content.split_once(&next).unwrap();
        //dbg!(section);
        //dbg!(&rest[0..10]);
        sections.push((id - 1, section));
        content = rest;
    }

    sections.push((680, content));

    let string = serde_json::to_string(&sections).unwrap();
    std::fs::write("sections.json", string).unwrap();

    load_existing();
}

fn load_existing() {
    let mut all = Vec::new();

    let paths = std::fs::read_dir("../fabbwled-backend/src/main/resources/sections/").unwrap();
    for file in paths {
        let file = file.unwrap();
        let content = std::fs::read_to_string(file.path()).unwrap();
        let content = serde_yaml::from_str::<Value>(&content).unwrap();
        if content.is_null() {
            continue;
        }
        let sections = content.as_sequence().unwrap();
        for section in sections {
            all.push(section["number"].as_u64().unwrap());
        }
    }

    std::fs::write("existing.json", serde_json::to_string(&all).unwrap()).unwrap();
}
