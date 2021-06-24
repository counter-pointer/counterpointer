const KEY_MAP = {
  TOGGLE_HELP: ["shift+s"],

  MOVE_LEFT: ["ArrowLeft"],
  MOVE_RIGHT: ["ArrowRight"],
  DESELECT: ["esc"],
  SHARP_NOTE: ["="],
  FLAT_NOTE: ["-"],
  RAISE_NOTE_PITCH: ["ArrowUp"],
  LOWER_NOTE_PITCH: ["ArrowDown"],
  RAISE_NOTE_PITCH_OCTAVE: ["]"],
  LOWER_NOTE_PITCH_OCTAVE: ["["],
  SET_NOTE_LENGTH_WHOLE: ["1"],
  SET_NOTE_LENGTH_HALF: ["2"],
  SET_NOTE_LENGTH_QUARTER: ["3"],
  DELETE_NOTE: ["del", "backspace"],
  ADD_NOTE_A: ["a"],
  ADD_NOTE_B: ["b"],
  ADD_NOTE_C: ["c"],
  ADD_NOTE_D: ["d"],
  ADD_NOTE_E: ["e"],
  ADD_NOTE_F: ["f"],
  ADD_NOTE_G: ["g"],
  ADD_REST: ["z"],
  
  
  CHECK: ["enter"],
  GENERATE: ["shift+enter"],
  PLAY: ["space"],
};

// disable keys used by the react hotkeys
document.addEventListener("keydown", function(e) {
  if(["Space","Enter","ArrowUp","ArrowDown","ArrowLeft","ArrowRight"].indexOf(e.code) > -1) {
      e.preventDefault();
  }
}, false);

export default KEY_MAP;