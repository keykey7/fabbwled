import {
  Button,
  Container,
  FormControl,
  Grid,
  InputAdornment,
  InputLabel,
  MenuItem,
  Select,
  TextField,
  Typography,
} from "@mui/material";
import "./adventure-sheet.scss";
import { useFormik } from "formik";
import validationSchema from "./AdventureSheetSchema.ts";
import { setCharacter } from "../../../api/character.ts";
import { useState } from "react";
import AlertMessage from "../../../components/AlertMessage/AlertMessage.tsx";
import { useNavigate } from "react-router";
import { Player } from "../../../interfaces/character.ts";

const professions: Profession[] = [
  "WAYFARER",
  "WARRIOR",
  "MAGE",
  "ROGUE",
  "PRIEST",
  "TROUBADOUR",
];

const blessings: Blessings[] = [
  "CHARISMA",
  "COMBAT",
  "MAGIC",
  "SANCTITY",
  "SCOUTING",
  "THIEVERY",
];

const defaultPossessions = ["sword", "leather jerkin (Defence +1)", "map"];

const initialValues = {
  name: "",
  profession: professions[0],
  rank: 1,
  charisma: 1,
  combat: 1,
  magic: 1,
  sanctity: 1,
  scouting: 1,
  thievery: 1,
  staminaCurrent: 9,
  staminaWhenUnwounded: 9,
  possessions: defaultPossessions,
  titlesAndHonours: "",
  money: 16,
  description: "",
  god: "",
  blessings: [blessings[0]],
};
export default function AdventureSheet() {
  const navigate = useNavigate();
  const [errorMessage, setErrorMessage] = useState("");

  const formik = useFormik({
    initialValues,
    validationSchema,
    onSubmit: async () => {
      const player: Player = {
        name: formik.values.name,
        currentSection: "1-1",
        titlesAndHonours: formik.values.titlesAndHonours
          .split(",")
          .map((title) => title.trim()),
        rank: "OUTCAST", // must be OUTCAST (starting rank 1)
        profession: formik.values.profession,
        stamina: formik.values.staminaCurrent,
        staminaWhenUnwounded: formik.values.staminaWhenUnwounded,
        baseStats: {
          charisma: formik.values.charisma,
          combat: formik.values.combat,
          magic: formik.values.magic,
          sanctity: formik.values.sanctity,
          scouting: formik.values.scouting,
          thievery: formik.values.thievery,
        },
        possessions: formik.values.possessions,
        shards: formik.values.money,
        defence: 0, // This value will be calculated in the backend
        tickBoxes: {},
        codeWords: [],
        blessings: formik.values.blessings,
        poisons: [],
        disease: [],
        curses: [],
        persistentSectionStore: {},
        mostRecentDiceRoll: [],
      };
      try {
        await setCharacter(player);
        setErrorMessage("");
        navigate("/game");
      } catch (error) {
        setErrorMessage("Failed to create character. Please try again.");
        console.error(error);
      }
    },
  });

  return (
    <div>
      <br />
      <Container className="background">
        <Typography variant="h4" gutterBottom style={{ textAlign: "center" }}>
          Adventure Sheet
        </Typography>
        <hr />
        <Typography variant="h5" gutterBottom style={{ textAlign: "center" }}>
          Base stats
        </Typography>
        <form onSubmit={formik.handleSubmit}>
          <Grid container>
            {/* Row 1 */}
            <Grid item xs={3} />
            <Grid item xs={3}>
              <TextField
                label="Name"
                variant="standard"
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.name}
                error={formik.touched.name && Boolean(formik.errors.name)}
                helperText={formik.touched.name && formik.errors.name}
                name="name"
              />
            </Grid>
            <Grid item xs={2}>
              <FormControl variant="standard" sx={{ width: "100%" }}>
                <InputLabel>Profession</InputLabel>
                <Select
                  defaultValue={"Wayfarer"}
                  onChange={formik.handleChange}
                  value={formik.values.profession}
                  name="profession"
                >
                  {professions.map((profession) => (
                    <MenuItem key={profession} value={profession}>
                      {profession}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={3} />

            {/* Row 2 */}
            <Grid item xs={3} />
            <Grid item xs={3}>
              <TextField
                label="God"
                variant="standard"
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.god}
                error={formik.touched.god && Boolean(formik.errors.god)}
                helperText={formik.touched.god && formik.errors.god}
                name="god"
              />
            </Grid>
            <Grid item xs={3}>
              <TextField
                label="Rank"
                variant="standard"
                disabled
                value={formik.values.rank}
                name="rank"
              />
            </Grid>
            <Grid item xs={4} />
          </Grid>
          <br />

          <hr />
          <Typography variant="h5" gutterBottom style={{ textAlign: "center" }}>
            Abilities
          </Typography>

          {/* Row 3 */}
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
                  max: 6,
                }}
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.charisma}
                error={
                  formik.touched.charisma && Boolean(formik.errors.charisma)
                }
                helperText={formik.touched.charisma && formik.errors.charisma}
                name="charisma"
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
                  max: 6,
                }}
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.combat}
                error={formik.touched.combat && Boolean(formik.errors.combat)}
                helperText={formik.touched.combat && formik.errors.combat}
                name="combat"
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
                  max: 6,
                }}
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.magic}
                error={formik.touched.magic && Boolean(formik.errors.magic)}
                helperText={formik.touched.magic && formik.errors.magic}
                name="magic"
              />
            </Grid>
            <Grid item xs={3} />

            {/* Row 4 */}
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
                  max: 6,
                }}
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.sanctity}
                error={
                  formik.touched.sanctity && Boolean(formik.errors.sanctity)
                }
                helperText={formik.touched.sanctity && formik.errors.sanctity}
                name="sanctity"
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
                  max: 6,
                }}
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.scouting}
                error={
                  formik.touched.scouting && Boolean(formik.errors.scouting)
                }
                helperText={formik.touched.scouting && formik.errors.scouting}
                name="scouting"
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
                  max: 6,
                }}
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.thievery}
                error={
                  formik.touched.thievery && Boolean(formik.errors.thievery)
                }
                helperText={formik.touched.thievery && formik.errors.thievery}
                name="thievery"
              />
            </Grid>
            <Grid item xs={3} />
          </Grid>
          <br />

          {/* Row 5 */}
          <hr />
          <Typography variant="h5" gutterBottom style={{ textAlign: "center" }}>
            Possessions
          </Typography>

          {/* Row 6 */}
          <Grid container>
            <Grid item xs={4} />
            <Grid item xs={4}>
              <FormControl variant="standard">
                <InputLabel>Your possessions</InputLabel>
                <Select
                  multiple
                  value={formik.values.possessions}
                  fullWidth
                  disabled
                >
                  {defaultPossessions.map((possession) => (
                    <MenuItem key={possession} value={possession}>
                      {possession}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
          </Grid>
          <br />
          <hr />
          <Typography variant="h5" gutterBottom style={{ textAlign: "center" }}>
            Other values
          </Typography>

          {/* Row 7 */}
          <Grid container>
            <Grid item xs={3} />
            <Grid item xs={3}>
              <TextField
                label="Money"
                variant="standard"
                type="number"
                disabled
                InputLabelProps={{
                  shrink: true,
                }}
                inputProps={{
                  min: 1,
                  max: 6,
                }}
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start">$</InputAdornment>
                  ),
                }}
                value={formik.values.money}
                name="money"
              />
            </Grid>
            <Grid item xs={3}>
              <TextField
                label="Stamina"
                variant="standard"
                type="number"
                InputLabelProps={{
                  shrink: true,
                }}
                inputProps={{
                  min: 1,
                  max: 6,
                }}
                disabled
                value={formik.values.staminaCurrent}
                name="staminaCurrent"
              />
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
              <FormControl variant="standard" sx={{ width: "100%" }}>
                <InputLabel>Blessings</InputLabel>
                <Select
                  defaultValue={"Charisma"}
                  onChange={formik.handleChange}
                  onBlur={formik.handleBlur}
                  multiple
                  value={formik.values.blessings}
                  name="blessings"
                >
                  {blessings.map((blessing) => (
                    <MenuItem key={blessing} value={blessing}>
                      {blessing}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
          </Grid>
          <br />
          <Grid container>
            <Grid item xs={4} />
            <Grid item xs={4}>
              <TextField
                label="Titles and honours (comma separated)"
                fullWidth
                variant="standard"
                multiline
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.titlesAndHonours}
                error={
                  formik.touched.titlesAndHonours &&
                  Boolean(formik.errors.titlesAndHonours)
                }
                helperText={
                  formik.touched.titlesAndHonours &&
                  formik.errors.titlesAndHonours
                }
                name="titlesAndHonours"
              />
            </Grid>
          </Grid>

          {/* Row 8 */}
          <Grid container>
            <Grid item xs={4} />
            <Grid item xs={4}>
              <TextField
                label="Description"
                fullWidth
                variant="standard"
                multiline
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.description}
                error={
                  formik.touched.description &&
                  Boolean(formik.errors.description)
                }
                helperText={
                  formik.touched.description && formik.errors.description
                }
                name="description"
              />
            </Grid>
          </Grid>
          <br />
          <div className={"submitContainer"}>
            <Button className="submitButton" variant="contained" type="submit">
              Create Character
            </Button>
          </div>
        </form>
        <br />
        {errorMessage && (
          <AlertMessage
            severity="error"
            message={errorMessage}
            onClose={() => setErrorMessage("")}
          />
        )}
        <br />
      </Container>
      <br />
    </div>
  );
}
