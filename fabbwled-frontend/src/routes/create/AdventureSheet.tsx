import {
  Container,
  Grid,
  InputAdornment,
  TextField,
  Typography,
} from "@mui/material";
import { useState } from "react";
import "./adventure-sheet.scss";

type Abilities = {
  charisma: number;
  combat: number;
  magic: number;
  sanctity: number;
  scouting: number;
  thievery: number;
};

export default function AdventureSheet() {
  const [name, setName] = useState<string>("");
  const [abilities, setAbilities] = useState<Abilities>({
    charisma: 1,
    combat: 1,
    magic: 1,
    sanctity: 1,
    scouting: 1,
    thievery: 1,
  });

  const handleAbilityChange = (
    event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>,
    abilityName: keyof Abilities,
  ) => {
    const inputValue = parseInt(event.target.value);

    if (!isNaN(inputValue) && inputValue >= 1 && inputValue <= 10) {
      setAbilities((prevAbilities) => ({
        ...prevAbilities,
        [abilityName]: inputValue,
      }));
    }
  };

  const handleKeyPress = (event: {
    key: string;
    preventDefault: () => void;
  }) => {
    const allowedKeys = /[0-9]|Backspace|ArrowLeft|ArrowRight/;

    if (!allowedKeys.test(event.key)) {
      event.preventDefault();
    }
  };

  return (
    <div>
      <br />
      <Container>
        <Typography variant="h4" gutterBottom style={{ textAlign: "center" }}>
          Adventure Sheet
        </Typography>
        <Grid container>
          {/* Row 1 */}
          <Grid item xs={3} />
          <Grid item xs={3}>
            <TextField
              label="Name"
              variant="standard"
              onChange={(e) => setName(e.target.value)}
            />
          </Grid>
          <Grid item xs={3}>
            <TextField label="Profession" variant="standard" />
          </Grid>
          <Grid item xs={3} />

          {/* Row 2 */}
          <Grid item xs={3} />
          <Grid item xs={3}>
            <TextField label="God" variant="standard" />
          </Grid>
          <Grid item xs={0.75}>
            <TextField label="Rank" variant="standard" />
          </Grid>
          <Grid item xs={0.5} />
          <Grid item xs={0.75}>
            <TextField label="Defence" variant="standard" />
          </Grid>
          <Grid item xs={4} />
        </Grid>
        <br />
        <Typography variant="h5" gutterBottom style={{ textAlign: "center" }}>
          {name === "Nikolaj" ? "Disabilities" : "Abilities"}
        </Typography>
        <Grid container>
          <Grid item xs={3} />
          <Grid item xs={1}>
            <TextField
              label="Charisma"
              variant="standard"
              type="number"
              InputLabelProps={{
                shrink: true,
              }}
              inputProps={{
                min: 1,
                max: 10,
              }}
              onKeyPress={handleKeyPress}
              onChange={(e) => handleAbilityChange(e, "charisma")}
            />
          </Grid>
          <Grid item xs={1} />
          <Grid item xs={1}>
            <TextField
              label="Combat"
              variant="standard"
              type="number"
              InputLabelProps={{
                shrink: true,
              }}
              inputProps={{
                min: 1,
                max: 10,
              }}
              onKeyPress={handleKeyPress}
              onChange={(e) => handleAbilityChange(e, "combat")}
            />
          </Grid>
          <Grid item xs={1} />
          <Grid item xs={1}>
            <TextField
              label="Magic"
              variant="standard"
              type="number"
              InputLabelProps={{
                shrink: true,
              }}
              inputProps={{
                min: 1,
                max: 10,
              }}
              onKeyPress={handleKeyPress}
              onChange={(e) => handleAbilityChange(e, "magic")}
            />
          </Grid>
          <Grid item xs={3} />

          {/* Row 3 */}
          <Grid item xs={3} />
          <Grid item xs={1}>
            <TextField
              label="Sanctity"
              variant="standard"
              type="number"
              InputLabelProps={{
                shrink: true,
              }}
              inputProps={{
                min: 1,
                max: 10,
              }}
              onKeyPress={handleKeyPress}
              onChange={(e) => handleAbilityChange(e, "sanctity")}
            />
          </Grid>
          <Grid item xs={1} />
          <Grid item xs={1}>
            <TextField
              label="Scouting"
              variant="standard"
              type="number"
              InputLabelProps={{
                shrink: true,
              }}
              inputProps={{
                min: 1,
                max: 10,
              }}
              onKeyPress={handleKeyPress}
              onChange={(e) => handleAbilityChange(e, "scouting")}
            />
          </Grid>
          <Grid item xs={1} />
          <Grid item xs={1}>
            <TextField
              label="Thievery"
              variant="standard"
              type="number"
              InputLabelProps={{
                shrink: true,
              }}
              inputProps={{
                min: 1,
                max: 10,
              }}
              onKeyPress={handleKeyPress}
              onChange={(e) => handleAbilityChange(e, "thievery")}
            />
          </Grid>
          <Grid item xs={3} />
        </Grid>
        <br />
        <Typography variant="h5" gutterBottom style={{ textAlign: "center" }}>
          Stamina
        </Typography>
        <Grid container>
          <Grid item xs={3} />
          <Grid item xs={3}>
            <TextField
              label="When unwounded"
              variant="standard"
              type="number"
              InputLabelProps={{
                shrink: true,
              }}
              inputProps={{
                min: 1,
                max: 10,
              }}
              onKeyPress={handleKeyPress}
            />
          </Grid>
          <Grid item xs={3}>
            <TextField
              label="Current"
              variant="standard"
              type="number"
              InputLabelProps={{
                shrink: true,
              }}
              inputProps={{
                min: 1,
                max: 10,
              }}
              onKeyPress={handleKeyPress}
            />
          </Grid>
        </Grid>
        <br />
        <Typography variant="h5" gutterBottom style={{ textAlign: "center" }}>
          Possesions
        </Typography>
        <Grid container>{/* TODO NOEL: implement possesions */}</Grid>
        <br />
        <Typography variant="h5" gutterBottom style={{ textAlign: "center" }}>
          Other values
        </Typography>
        <Grid container>
          <Grid item xs={3} />
          <Grid item xs={3}>
            <TextField
              label="Money"
              variant="standard"
              type="number"
              InputLabelProps={{
                shrink: true,
              }}
              inputProps={{
                min: 1,
                max: 10,
              }}
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">$</InputAdornment>
                ),
              }}
              onKeyPress={handleKeyPress}
            />
          </Grid>
          <Grid item xs={3}>
            <TextField label="Blessings" variant="standard" multiline />
          </Grid>
          <Grid item xs={3} />

          <Grid item xs={3} />
          <Grid item xs={3}>
            <TextField
              label="Resurrection arrangements"
              variant="standard"
              multiline
            />
          </Grid>
          <Grid item xs={3}>
            <TextField
              label="Titles and honours"
              variant="standard"
              multiline
            />
          </Grid>
        </Grid>
      </Container>
    </div>
  );
}
