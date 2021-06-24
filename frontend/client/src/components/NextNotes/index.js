import { Box, makeStyles } from "@material-ui/core";
import { brown } from "@material-ui/core/colors";
import Note from "abceditor/lib/Score/Note";
import PropTypes from "prop-types";
import React from "react";
import NextNote from "./NextNote";

const useStyles = makeStyles({
  root: {
    borderLeft: "2px solid",
    borderColor: brown[400],
    backgroundColor: brown[100],
    overflowY: "auto",
    display: "flex",
    flexDirection: "column",
    width: 66,
  }
});

const NextNotes = ({notes, select}) => {
  const classes = useStyles();
  return <Box className={classes.root}>
    {
      notes.map((note, index) => <NextNote key={index} note={note} select={() => select(note)}/>)
    }
  </Box>;
}

NextNotes.propTypes = {
  note: PropTypes.arrayOf(PropTypes.instanceOf(Note)),
  select: PropTypes.func,
};

export default NextNotes;