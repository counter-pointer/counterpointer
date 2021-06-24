import { AppBar, Box, Button, createMuiTheme, Icon, IconButton, makeStyles, ThemeProvider, Toolbar, Tooltip } from "@material-ui/core";
import React from "react";
import Clear from "./Clear";
import { KEY_SIGNATURE_DATA, MELODY_UPPER_DATA, MODE_DATA, SPECIES_DATA } from "./data";
import Dropdown from "./Dropdown";
import Load from "./Load";

const useStyles = makeStyles({
  right: {
    marginLeft: "auto",
  },
  inline: {
    display: "inline",
  }
});

const Navbar = ({species, setSpecies, melodyUpper, setMelodyUpper, keySignature, setKeySignature, mode, setMode, check, generate, clearMelody, clearCounterMelody, play, isPlaying, download, load, setShowHelp}) => {
  const classes = useStyles();

  return <ThemeProvider theme={theme => createMuiTheme({
    ...theme,
    palette: {
      primary: theme.palette.primary,
      secondary: theme.palette.secondary,
      type: "dark"
    }
  })}>
    <AppBar position="static">
      <Toolbar>
        <Box>
          <Tooltip title="Which species counterpoint to write">
            <Box className={classes.inline}>
              <Dropdown value={species} onChange={e => setSpecies(e.target.value)} data={SPECIES_DATA}/>
            </Box>
          </Tooltip>
          <Tooltip title="Whether the melody is the upper or the lower voice">
            <Box className={classes.inline}>
              <Dropdown value={melodyUpper} onChange={e => setMelodyUpper(e.target.value)} data={MELODY_UPPER_DATA}/>
            </Box>
          </Tooltip>
          <Tooltip title="Tonic of the key">
            <Box className={classes.inline}>
              <Dropdown value={keySignature} onChange={e => setKeySignature(e.target.value)} data={KEY_SIGNATURE_DATA}/>
            </Box>
          </Tooltip>
          <Tooltip title="Mode of the key">
            <Box className={classes.inline}>
              <Dropdown value={mode} onChange={e => setMode(e.target.value)} data={MODE_DATA}/>
            </Box>
          </Tooltip>
        </Box>
        <Box className={classes.right}>
          <Tooltip title="Lost? Click here to learn more about CounterPointer!">
            <Button onClick={() => setShowHelp(true)}>
              Help
            </Button>
          </Tooltip>
          <Load load={load}/>
          <Tooltip title="Checks current countermelody for the melody">
            <Button onClick={check}>
              Check
            </Button>
          </Tooltip>
          <Tooltip title="Generates a new or completes the current countermelody">
            <Button onClick={generate}>
              Generate
            </Button>
          </Tooltip>
          <Clear clearMelody={clearMelody} clearCounterMelody={clearCounterMelody}/>
          <Tooltip title="Play audio">
            <IconButton onClick={play} size="small">
              <Icon>{isPlaying ? "stop" : "play_arrow"}</Icon>
            </IconButton>
          </Tooltip>
          <Tooltip title="Download as PDF">
            <IconButton onClick={download} size="small">
              <Icon>download</Icon>
            </IconButton>
          </Tooltip>
        </Box>
      </Toolbar>
    </AppBar>
  </ThemeProvider>;
}

export default Navbar;