import { Divider, makeStyles, Typography } from "@material-ui/core";
import React from "react";

const useStyles = makeStyles({
  root: {
    paddingTop: "10px",
    paddingBottom: "10px",
  }
});

const HotKeyHeader = ({title}) => {
  const classes = useStyles();
  return <>
    <Typography variant="h6" className={classes.root}>{title}</Typography>
    <Divider/>
  </>;
}

export default HotKeyHeader;