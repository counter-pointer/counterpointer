import { Box, Divider, makeStyles } from "@material-ui/core";
import React from "react";
import HotKeyDisplay from "./HotKeyDisplay";

const useStyles = makeStyles({
  root: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    margin: "10px 0",
    width: "100%",
  },
  keys: {
    display: "flex",
    flexDirection: "row",
    fontWeight: 600,
    marginRight: "2px",
  },
})

const HotKeyRow = ({title, hotkey}) => {
  const classes = useStyles();
  return <>
    <Box className={classes.root}>
      <Box component="span">{title}</Box>
      <Box className={classes.keys}>
        {
          hotkey.map((key, index) => 
            <React.Fragment key={index}>
              <HotKeyDisplay hotkey={key}/>
              {index !== (hotkey.length - 1) ? "/" : null}
            </React.Fragment>
          )
        }
      </Box>
    </Box>
    <Divider/>
  </>;
}

export default HotKeyRow;