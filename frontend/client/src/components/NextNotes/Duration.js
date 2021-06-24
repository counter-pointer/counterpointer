import { Icon } from "@material-ui/core";
import React from "react";
import WholeNote from "../../assets/wholeNote.png";
import HalfNote from "../../assets/halfNote.png";
import QuarterNote from "../../assets/quarterNote.png";
import PropTypes from "prop-types";
import Note from "abceditor/lib/Score/Note";


const durationToSymbol = (duration) => {
  switch (duration) {
    case 1:
      return QuarterNote;
    case 2:
      return HalfNote;
    case 4:
      return WholeNote;
    default:
      return QuarterNote;
  }
}

const Duration = ({note}) => {
  return <Icon fontSize="inherit">
    <img src={durationToSymbol(note.duration)} alt="duration" width={14} height={14}/>
  </Icon>;
}

Duration.propTypes = {
  note: PropTypes.instanceOf(Note),
};

export default Duration;