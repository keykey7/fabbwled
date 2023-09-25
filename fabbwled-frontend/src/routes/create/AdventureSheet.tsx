import {Container, Grid, Paper, TextField, Typography} from "@mui/material";
import React from "react";
import './adventure-sheet.scss'

export default function AdventureSheet() {
    return <div><br/>
        <Container>
            <Typography variant="h4" gutterBottom style={{textAlign: "center"}}>
                Adventure Sheet
            </Typography>
            <Grid container spacing={3}>
                {/* Row 1 */}
                <Grid item xs={3}/>
                <Grid item xs={3}>
                    <TextField label="Name" variant="standard"/>
                </Grid>
                <Grid item xs={3}>
                    <TextField label="Profession" variant="standard"/>
                </Grid>
                <Grid item xs={3}/>
                <Grid/>

                {/* Row 2 */}
                <Grid item xs={3}/>
                <Grid item xs={3}>
                    <TextField label="God" variant="standard"/>
                </Grid>
                <Grid item xs={1}>
                    <TextField label="Rank" variant="standard"/>
                </Grid>
                <Grid item xs={1}>
                    <TextField label="Defence" variant="standard"/>
                </Grid>
                <Grid item xs={3}/>
                <Grid/>
            </Grid>
        </Container>
    </div>
        ;
}
