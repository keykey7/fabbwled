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
import { createCharacter } from "../../api/character.ts";

const defaultPossessions = ["sword", "leather jerkin (Defence +1)", "map"];
export default function AdventureSheet() {
  const initialValues = {
    name: "",
    profession: "Wayfarer",
    rank: 1,
    charisma: 1,
    combat: 1,
    magic: 1,
    sanctity: 1,
    scouting: 1,
    thievery: 1,
    staminaUnwounded: 9,
    staminaCurrent: 9,
    possessions: defaultPossessions,
    titlesAndHonours: "",
    money: 16,
  };

  const formik = useFormik({
    initialValues,
    validationSchema,
    onSubmit: (values) => {
      console.log("Form submitted with values:", values);

      const characterCreateDto: CharacterCreateDto = {
        player: {
          name: formik.values.name,
          currentSection: { bookId: 0, sectionId: 0 },
          titlesAndHonours: formik.values.titlesAndHonours
            .split(",")
            .map((title) => title.trim()),
          rank: "OUTCAST", // must be OUTCAST (starting rank 1)
          profession: "Wayfarer",
          stamina: formik.values.staminaCurrent,
          baseStats: {
            charisma: formik.values.charisma,
            combat: formik.values.combat,
            magic: formik.values.magic,
            sanctity: formik.values.sanctity,
            scouting: formik.values.scouting,
            thievery: formik.values.thievery,
          },
          possessions: formik.values.possessions,
          shards: { shardCount: formik.values.money },
          defence: 0, // This value will be calculated in the backend
          tickBoxes: {
            additionalProp1: 0,
            additionalProp2: 0,
            additionalProp3: 0,
          },
          codeWords: [],
        },
        description: "",
      };
      createCharacter(characterCreateDto).then((r) => console.log(r));
    },
  });

  return (
    <div>
      <br />
      <Container>
        <Typography variant="h4" gutterBottom style={{ textAlign: "center" }}>
          Adventure Sheet
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
                  <MenuItem value={"Wayfarer"}>Wayfarer</MenuItem>
                  <MenuItem value={"Warrior"}>Warrior</MenuItem>
                  <MenuItem value={"Mage"}>Mage</MenuItem>
                  <MenuItem value={"Rogue"}>Rogue</MenuItem>
                  <MenuItem value={"Priest"}>Priest</MenuItem>
                  <MenuItem value={"Troubadour"}>Troubadour</MenuItem>
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={3} />

            {/* Row 2 */}
            <Grid item xs={3} />
            <Grid item xs={3}>
              <TextField label="God" variant="standard" />
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
          <Typography variant="h5" gutterBottom style={{ textAlign: "center" }}>
            Stamina
          </Typography>

          {/* Row 5 */}
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
                  max: 6,
                }}
                disabled
                value={formik.values.staminaUnwounded}
                name="staminaUnwounded"
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
                  max: 6,
                }}
                disabled
                value={formik.values.staminaCurrent}
                name="staminaCurrent"
              />
            </Grid>
          </Grid>
          <br />
          <Typography variant="h5" gutterBottom style={{ textAlign: "center" }}>
            Possessions
          </Typography>

          {/* Row 6 */}
          <Grid container>
            <Grid item xs={4.5} />
            <Grid item>
              <FormControl variant="standard">
                <InputLabel>Your possessions</InputLabel>
                <Select multiple value={formik.values.possessions} disabled>
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
          <br />
          <div className={"submitContainer"}>
            <Button variant="contained" type="submit">
              Create Character
            </Button>
          </div>
        </form>
        <br />
        <br />
      </Container>
    </div>
  );
}
