import { Button, Tooltip } from "@material-ui/core";
import Note from "abceditor/lib/Score/Note";
import PropTypes from "prop-types";
import React from "react";
import Duration from "./Duration";
import Rest from "./Rest";

const NextNote = ({note, select}) => {
  if (note.pitch === null) {
    return <Button onClick={select}>
      <Rest duration={note.duration}/>
    </Button>
  }
  return <Tooltip title="Click to append suggested note to countermelody">
    <Button onClick={select}>
      { note.pitch.toString() } <Duration note={note}/>
    </Button>
  </Tooltip>
}

NextNote.propTypes = {
  note: PropTypes.instanceOf(Note),
  select: PropTypes.func,
};

export default NextNote;