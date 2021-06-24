import { Box, makeStyles } from "@material-ui/core";
import React from "react";
import HotKey from "./HotKey";

const useStyles = makeStyles({
  root: {
    display: "inline-block",
    transform: "translateY(-1px)",
  }
});

const HotKeyDisplay = ({hotkey}) => {
  const classes = useStyles();
  const keys = hotkey.split("+");
  return <Box className={classes.root}>
    { keys.map((key, index) => <HotKey key={index} hotkey={key}/>)}
  </Box>;
}

export default HotKeyDisplay;