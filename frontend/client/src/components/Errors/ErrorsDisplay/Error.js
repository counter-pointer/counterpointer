import { Box, makeStyles, Tooltip } from "@material-ui/core";
import React from "react";

const useStyles = makeStyles({
  inline: {
    display: "inline",
  }
});


const Error = ({error}) => {
  const classes = useStyles();
  return <Box>
    <Tooltip title={error.description} placement="right">
      <Box className={classes.inline}>
        {error.index}. Measure <Box component="span" fontWeight="fontWeightBold">{error.measure + 1}</Box> Beat <Box component="span" fontWeight="fontWeightBold">{error.beat + 1}</Box>: {error.name}
      </Box>
    </Tooltip>
  </Box>;
}

export default Error;