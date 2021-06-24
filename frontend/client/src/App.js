import { Box, createMuiTheme, CssBaseline, makeStyles, ThemeProvider } from "@material-ui/core";
import abceditor from "abceditor";
import Score from "abceditor/lib/Score";
import Key from "abceditor/lib/Score/Key";
import Role from "abceditor/lib/Score/Score/Role";
import React, { useEffect, useRef, useState } from "react";
import { GlobalHotKeys } from "react-hotkeys";
import { checkScore, generateScore } from "./api";
import Editor from "./components/Editor";
import Errors from "./components/Errors";
import Help from "./components/Help";
import Navbar from "./components/Navbar";
import NextNotes from "./components/NextNotes";
import PlaybackBar from "./components/PlaybackBar";
import WarningBanner from "./components/WarningBanner";
import KEY_MAP from "./hotkeys";

const theme = createMuiTheme({
  palette: {
    primary: {
      main: "#141414"
    },
    secondary: {
      main: "#EEF0F2",
    },
  },
  typography: {
    fontFamily: [
      'Roboto',
      'MetDemo',
      '"Helvetica Neue"',
      'Arial',
      'sans-serif',
      '-apple-system',
    ].join(','),
  },
})

const useStyles = makeStyles({
  root: {
    width: "100vw",
    height: "100vh",
    display: "flex",
    flexDirection: "column",
    overflow: "hidden",
  },
  body: {
    flex: "1 1 auto",
    overflowY: "auto",
    minHeight: 0,
    display: "flex",
  }
})

function App() {
  const DEFAULT_KEY_SIGNATURE = "C";
  const DEFAULT_MODE = "maj";
  const DEFAULT_SPECIES = 1;
  const DEFAULT_MELODY_UPPER = true;

  const abceditorRef = useRef(null);
  const editorRef = useRef(null);
  const [species, setSpecies] = useState(DEFAULT_SPECIES);
  const [melodyUpper, setMelodyUpper] = useState(DEFAULT_MELODY_UPPER);
  const [keySignature, setKeySignature] = useState(DEFAULT_KEY_SIGNATURE);
  const [mode, setMode] = useState(DEFAULT_MODE);
  const [errorsExpanded, setErrorsExpanded] = useState(true);
  const [errors, setErrors] = useState([]);
  const [nextNotes, setNextNotes] = useState([]);
  const [isPlaying, setIsPlaying] = useState(false);
  const [playbackProgress, setPlaybackProgress] = useState(0);
  const [warningMessage, setWarningMessage] = useState(null);
  const [showHelp, setShowHelp] = useState(true);
  const classes = useStyles();

  // reset editor every time one of these values change
  useEffect(() => {
    abceditorRef.current = new abceditor.Editor(
      new abceditor.Score(Key.fromString(keySignature + mode), melodyUpper),
      species
    );
    editorRef.current.rerender();
  }, [keySignature, mode, species, melodyUpper]);

  const updateKeySignature = (keySignature) => {
    setKeySignature(keySignature);
    abceditorRef.current.setKey(Key.fromString(keySignature + mode));
    editorRef.current.rerender();
  }

  const updateMode = (mode) => {
    setMode(mode);
    abceditorRef.current.setKey(Key.fromString(keySignature + mode));
    editorRef.current.rerender();
  }

  const check = () => {
    checkScore(abceditorRef.current.score, species).then(setErrors).catch(e => setWarningMessage(e.message));
  }

  const generate = () => {
    generateScore(abceditorRef.current.score, species).then(r => {
      if (r.status === 'failure') {
        setWarningMessage(`There is no possible countermelody for this melody that fulfills all rules.`)
      } else {
        abceditorRef.current.setScore(Score.fromJSON(r.score));
        editorRef.current.rerender();
      }
    }).catch(e => {
      setWarningMessage(e.message)
    });
  }

  const clearMelody = () => {
    abceditorRef.current.clearMelodyByRole(Role.MELODY);
    editorRef.current.rerender();
  }

  const clearCounterMelody = () => {
    abceditorRef.current.clearMelodyByRole(Role.COUNTERMELODY);
    editorRef.current.rerender();
  }

  const play = () => {
    editorRef.current.play();
  }

  const stop = () => {
    editorRef.current.stop();
  }

  const handlePlay = () => {
    if (isPlaying) {
      stop();
    } else {
      play();
    }
  }

  const download = () => {
    editorRef.current.download();
  }

  const selectNote = (note) => {
    abceditorRef.current.addSuggestion(note);
    editorRef.current.rerender();
  }

  const load = (setup) => {
    setSpecies(setup.species);
    setMelodyUpper(setup.score.isMelodyHigher);
    setKeySignature(setup.keySignature);
    setMode(setup.mode);
    setTimeout(() => {
      abceditorRef.current.species = setup.species;
      const score = Score.fromJSON(JSON.parse(JSON.stringify(setup.score)));
      score.isMelodyHigher = setup.score.isMelodyHigher;
      abceditorRef.current.setScore(score);
      editorRef.current.rerender();
    }, 100);
  }

  return (
    <GlobalHotKeys keyMap={KEY_MAP} allowChanges handlers={{
      PLAY: handlePlay,
      CHECK: check,
      GENERATE: generate,
      TOGGLE_HELP: () => setShowHelp(!showHelp),
    }}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <Box className={classes.root}>
          {warningMessage ? <WarningBanner message={warningMessage} reset={() => setWarningMessage(null)}/> : null}
          <Help show={showHelp} setShow={setShowHelp}/>
          <Navbar
            species={species}
            setSpecies={setSpecies}
            melodyUpper={melodyUpper}
            setMelodyUpper={setMelodyUpper}
            keySignature={keySignature}
            setKeySignature={updateKeySignature}
            mode={mode}
            setMode={updateMode}
            check={check}
            generate={generate}
            clearMelody={clearMelody}
            clearCounterMelody={clearCounterMelody}
            play={handlePlay}
            isPlaying={isPlaying}
            download={download}
            load={load}
            setShowHelp={setShowHelp}
          />
          <PlaybackBar progress={playbackProgress}/>
          <Box className={classes.body}>
            <Editor ref={editorRef} setIsPlaying={setIsPlaying} setProgress={setPlaybackProgress} setNextNotes={setNextNotes} abceditorRef={abceditorRef} species={species}/>
            <NextNotes notes={nextNotes} select={selectNote}/>
          </Box>
          <Errors errors={errors} expanded={errorsExpanded} setExpanded={setErrorsExpanded}/>
        </Box>
      </ThemeProvider>
    </GlobalHotKeys>
  );
}

export default App;
