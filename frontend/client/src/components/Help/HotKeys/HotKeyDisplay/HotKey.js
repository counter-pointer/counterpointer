import { Box, makeStyles } from "@material-ui/core";
import React from "react";

const useStyles = makeStyles({
  root: {
    color: "white",
    backgroundColor: "#686868",
    boxShadow: "0px 4px #222222",
    display: "inline",
    padding: "2px 4px 0px",
    lineHeight: 0.9,
    borderRadius: "2px",
    margin: "0 1px",
    fontWeight: 600,
  }
});

const convertHotkey = (s) => {
  switch (s) {
    case "left":
      return "←";
    case "right":
      return "→";
    default:
      return s.toUpperCase();
  }
}

const HotKey = ({hotkey}) => {
  const classes = useStyles();
  const displayString = convertHotkey(hotkey);
  return <Box className={classes.root} component="span">
    {displayString}
  </Box>
}

export default HotKey;