import { LinearProgress, makeStyles } from "@material-ui/core";
import { red } from "@material-ui/core/colors";
import React from "react";

const useStyles = makeStyles(theme => ({
  root: {
    height: 10,
    minHeight: 10,
  },
  linearColorPrimary: {
    backgroundColor: theme.palette.secondary.dark,
  },
  linearBarColorPrimary: {
    backgroundColor: red[600],
  }
}));

const PlaybackBar = ({progress}) => {
  const classes = useStyles();
  return <LinearProgress
    className={classes.root}
    classes={{
      colorPrimary: classes.linearColorPrimary,
      barColorPrimary: classes.linearBarColorPrimary,
    }}
    variant="determinate" 
    color="primary" 
    value={progress * 100}
  />
}

export default PlaybackBar;