import { Box, makeStyles } from "@material-ui/core";
import React from "react";
import ErrorBar from "./ErrorBar";
import ErrorsDisplay from "./ErrorsDisplay";

const useStyles = makeStyles({
  root: {
  }
})

const Errors = ({errors, expanded, setExpanded}) => {
  const classes = useStyles();
  return <Box className={classes.root}>
    <ErrorBar numErrors={errors.length} expanded={expanded} setExpanded={setExpanded}/>
    <ErrorsDisplay expanded={expanded} errors={errors}/>
  </Box>;
}

export default Errors;