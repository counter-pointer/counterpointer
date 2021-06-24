import { Box } from "@material-ui/core";
import React from "react";
import KEY_MAP from "../../../hotkeys";
import HotKeyRow from "./HotKeyRow";
import HotKeyHeader from "./HotKeyHeader";

const HotKeys = () => {
  return <Box>
    <HotKeyRow title="Toggle help" hotkey={KEY_MAP["TOGGLE_HELP"]}/>
    <HotKeyHeader title="Navigation"/>
    <HotKeyRow title="Check score" hotkey={KEY_MAP["CHECK"]}/>
    <HotKeyRow title="Generate countermelody" hotkey={KEY_MAP["GENERATE"]}/>
    <HotKeyRow title="Playback audio" hotkey={KEY_MAP["PLAY"]}/>

    <HotKeyHeader title="Editor"/>
    <HotKeyRow title="Move selection left" hotkey={KEY_MAP["MOVE_LEFT"]}/>
    <HotKeyRow title="Move selection right" hotkey={KEY_MAP["MOVE_RIGHT"]}/>
    <HotKeyRow title="Unselect current note" hotkey={KEY_MAP["DESELECT"]}/>
    <HotKeyRow title="Sharp selected note" hotkey={KEY_MAP["SHARP_NOTE"]}/>
    <HotKeyRow title="Flat selected note" hotkey={KEY_MAP["FLAT_NOTE"]}/>
    <HotKeyRow title="Raise selected note by 1 scale degree" hotkey={KEY_MAP["RAISE_NOTE_PITCH"]}/>
    <HotKeyRow title="Lower selected note by 1 scale degree" hotkey={KEY_MAP["LOWER_NOTE_PITCH"]}/>
    <HotKeyRow title="Raise selected note by 1 octave" hotkey={KEY_MAP["RAISE_NOTE_PITCH_OCTAVE"]}/>
    <HotKeyRow title="Lower selected note by 1 octave" hotkey={KEY_MAP["LOWER_NOTE_PITCH_OCTAVE"]}/>
    <HotKeyRow title="Set selected note to whole note" hotkey={KEY_MAP["SET_NOTE_LENGTH_WHOLE"]}/>
    <HotKeyRow title="Set selected note to half note" hotkey={KEY_MAP["SET_NOTE_LENGTH_HALF"]}/>
    <HotKeyRow title="Set selected note to quarter note" hotkey={KEY_MAP["SET_NOTE_LENGTH_QUARTER"]}/>
    <HotKeyRow title="Delete selected note" hotkey={KEY_MAP["DELETE_NOTE"]}/>
    <HotKeyRow title="Append note C after the selected note" hotkey={KEY_MAP["ADD_NOTE_C"]}/>
    <HotKeyRow title="Append note D after the selected note" hotkey={KEY_MAP["ADD_NOTE_D"]}/>
    <HotKeyRow title="Append note E after the selected note" hotkey={KEY_MAP["ADD_NOTE_E"]}/>
    <HotKeyRow title="Append note F after the selected note" hotkey={KEY_MAP["ADD_NOTE_F"]}/>
    <HotKeyRow title="Append note G after the selected note" hotkey={KEY_MAP["ADD_NOTE_G"]}/>
    <HotKeyRow title="Append note A after the selected note" hotkey={KEY_MAP["ADD_NOTE_A"]}/>
    <HotKeyRow title="Append note B after the selected note" hotkey={KEY_MAP["ADD_NOTE_B"]}/>
    <HotKeyRow title="Append rest after the selected note" hotkey={KEY_MAP["ADD_REST"]}/>
  </Box>;
}

export default HotKeys;
