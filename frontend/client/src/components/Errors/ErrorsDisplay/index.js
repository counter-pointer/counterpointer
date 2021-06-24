import { Box, makeStyles } from "@material-ui/core";
import React from "react";
import Error from "./Error";

const useStyles = makeStyles({
  root: {
    height: props => props.expanded ? 180 : 0,
    transition: "all 0.3s ease-in-out",
    overflowY: "auto",
  },
  errors: {
    padding: 10,
  }
});

const ErrorsDisplay = (props) => {
  const {errors} = props;
  const classes = useStyles(props);
  return <Box className={classes.root}>
    <Box className={classes.errors}>
      {errors.map((error, index) => <Error key={index} error={{...error, index: index+1}}/>)}
    </Box>
  </Box>;
}

export default ErrorsDisplay;