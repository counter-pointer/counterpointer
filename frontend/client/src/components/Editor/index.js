import { Box, makeStyles } from "@material-ui/core";
import Duration from "abceditor/lib/Score/Duration";
import ScaleValue from "abceditor/lib/Score/Pitch/ScaleValue";
import abcjs from "abcjs";
import { jsPDF } from 'jspdf';
import React, { forwardRef, useCallback, useEffect, useImperativeHandle, useRef, useState } from "react";
import { GlobalHotKeys } from "react-hotkeys";
import 'svg2pdf.js';
import { getSuggestions } from "../../api";

const useStyles = makeStyles({
  root: {
    flexGrow: 1,
    overflowY: "auto",
  },
  textarea: {
    display: "none",
  },
  paper: {
    width: "100%",
  },
  hiddenPaper: {
    display: "none",
  }
})

const Editor = forwardRef(({setIsPlaying, setProgress, setNextNotes, abceditorRef, species}, ref) => {
  const textRef = useRef(null);
  const editorRef = useRef(null);
  const synthRef = useRef(null);
  const [intervalId, setIntervalId] = useState(null);
  const classes = useStyles();

  const select = useCallback((index) => {
    textRef.current.setSelectionRange(index, index + 1);
    abceditorRef.current.selectNoteABC(index);
    editorRef.current.fireChanged();
  }, [textRef, abceditorRef]);
  
  const handleSelect = useCallback((e) => {
    select(e.target.selectionStart);
  }, [select]);

  useEffect(() => {
    editorRef.current = new abcjs.Editor("abc", {
      canvas_id: "paper",
      abcjsParams: {
        responsive: "resize",
      },
    });
    textRef.current.addEventListener("select", handleSelect);
    const text = textRef.current;
    return () => text.removeEventListener("select", handleSelect);
  }, [handleSelect]);

  const getSelection = () => {
    return editorRef.current.editarea.getSelection();
  };

  const getValue = () => {
    return textRef.current.value;
  }

  const setValue = (value) => {
    textRef.current.value = value;
    editorRef.current.fireChanged();
  }

  const stop = () => {
    if (intervalId != null) {
      clearInterval(intervalId);
      setIntervalId(null);
    }
    
    if (synthRef.current) {
      setIsPlaying(false);
      setProgress(0);
      synthRef.current.stop();
      synthRef.current = null;
    }
  };

  const play = useCallback(() => {
    const UPDATE_RATE = 100;
    const DELAY = 100;

    const synth = new abcjs.synth.CreateSynth();
    synthRef.current = synth;
    const visualObj = abcjs.renderAbc("hidden-paper", getValue());
    const audioContext = new AudioContext();
    setProgress(0);
    synth.init({
      audioContext,
      visualObj: visualObj[0],
      millisecondsPerMeasure: 2000,
    }).then(() => {
      return synth.prime();
    }).then(() => {
      const duration = synth.audioBuffers[0].duration;
      const startTime = new Date();
      // add small delay
      setTimeout(() => {
        // update progress bar
        setIsPlaying(true);
        const intervalId = setInterval(() => {
          const currentTime = new Date();
          const timeDiff = currentTime - startTime;
          const progress = Math.min(timeDiff / (1000 * duration), 1);
          setProgress(progress);
          // reset if playback is done
          if (progress === 1) {
            clearInterval(intervalId);
            setIntervalId(null);
            if (synthRef.current) {
              setIsPlaying(false);
              setProgress(1);
              synthRef.current.stop();
              synthRef.current = null;
            }
          }
        }, 1000 / UPDATE_RATE);
        setIntervalId(intervalId);
        synth.start();
      }, DELAY);
    });
  }, [synthRef, setIsPlaying, setProgress]);

  const download = () => {
    const doc = new jsPDF();
    
    // deselect things from the editor
    select(-1);
    const element = document.getElementById("paper").childNodes[0];
    doc.svg(element, {
      x: 0,
      y: 0,
      width: doc.internal.pageSize.getWidth(),
    }).then(() => {
      doc.save('sheet.pdf');
    });
  }

  const rerender = () => {
    console.log(abceditorRef.current.toABC());
    setValue(abceditorRef.current.toABC());
    select(abceditorRef.current.getSelectedIndexABC());
    if (abceditorRef.current.shouldSuggest()) {
      getSuggestions(abceditorRef.current.score, species).then(setNextNotes);
    } else {
      setNextNotes([]);
    }
  }

  useImperativeHandle(ref, () => ({
    getSelection,
    play,
    stop,
    download,
    rerender,
  }));

  const addNote = (note) => {
    const sv = (note === 'Z') ? null : ScaleValue.fromString(note);
    abceditorRef.current.addScaleValueAtSelected(sv);
    rerender();
  }

  const deleteNote = () => {
    abceditorRef.current.deleteSelectedNote();
    rerender();
  }

  const raiseNotePitch = () => {
    abceditorRef.current.raiseSelectedNote();
    rerender();
  }

  const lowerNotePitch = () => {
    abceditorRef.current.lowerSelectedNote();
    rerender();
  }

  const raiseNotePitchOctave = () => {
    abceditorRef.current.raiseOctaveSelectedNote();
    rerender();
  }

  const lowerNotePitchOctave = () => {
    abceditorRef.current.lowerOctaveSelectedNote();
    rerender();
  }

  const sharpNote = () => {
    abceditorRef.current.sharpSelectedNote();
    rerender();
  }

  const flatNote = () => {
    abceditorRef.current.flatSelectedNote();
    rerender();
  }

  const moveLeft = () => {
    abceditorRef.current.selectLeft();
    rerender();
  }

  const moveRight = () => {
    abceditorRef.current.selectRight();
    rerender();
  }

  const changeDuration = (duration) => {
    abceditorRef.current.changeDurationSelectedNote(duration);
    rerender();
  }

  const deselect = () => {
    abceditorRef.current.deselect();
    rerender();
  }

  return <GlobalHotKeys allowChanges handlers={{
    ADD_NOTE_A: () => addNote('A'),
    ADD_NOTE_B: () => addNote('B'),
    ADD_NOTE_C: () => addNote('C'),
    ADD_NOTE_D: () => addNote('D'),
    ADD_NOTE_E: () => addNote('E'),
    ADD_NOTE_F: () => addNote('F'),
    ADD_NOTE_G: () => addNote('G'),
    ADD_REST: () => addNote('Z'),
    DELETE_NOTE: deleteNote,
    RAISE_NOTE_PITCH: raiseNotePitch,
    LOWER_NOTE_PITCH: lowerNotePitch,
    RAISE_NOTE_PITCH_OCTAVE: raiseNotePitchOctave,
    LOWER_NOTE_PITCH_OCTAVE: lowerNotePitchOctave,
    SHARP_NOTE: sharpNote,
    FLAT_NOTE: flatNote,
    MOVE_LEFT: moveLeft,
    MOVE_RIGHT: moveRight,
    SET_NOTE_LENGTH_WHOLE: () => changeDuration(Duration.WHOLE),
    SET_NOTE_LENGTH_HALF: () => changeDuration(Duration.HALF),
    SET_NOTE_LENGTH_QUARTER: () => changeDuration(Duration.QUARTER),
    DESELECT: deselect,
  }}>
    <Box className={classes.root}>
      <Box>
        <Box id="paper" className={classes.paper}/>
        <Box id="hidden-paper" className={classes.hiddenPaper}/>
      </Box>
      <textarea id="abc" ref={textRef} className={classes.textarea}/>
    </Box>
  </GlobalHotKeys>;
});

export default Editor;