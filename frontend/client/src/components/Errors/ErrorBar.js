import { Box, Icon, IconButton, makeStyles, Tooltip, Typography } from "@material-ui/core";
import React from "react";

const useStyles = makeStyles(theme => ({
  root: {
    background: theme.palette.primary.main,
    display: "flex",
    alignItems: "center",
    padding: 6,
  },
  warning: {
    color: theme.palette.warning.main,
    display: "flex",
    userSelect: "none",
  },
  warningIcon: {
    marginRight: 4,
  },
  warningText: {
    fontSize: "1rem",
    lineHeight: "1.5",
  },
  dropdown: {
    color: theme.palette.secondary.main,
    marginLeft: "auto",
  }
}));

const ErrorBar = ({expanded, setExpanded, numErrors}) => {
  const classes = useStyles();
  return <Box className={classes.root}>
    <Tooltip title="Number of errors" placement="top">
      <Box className={classes.warning}>
        <Icon className={classes.warningIcon}>warning_amber</Icon>
        <Typography className={classes.warningText} variant="button">{numErrors}</Typography>
      </Box>
    </Tooltip>
    <Tooltip title={expanded ? "Minimize errors bar" : "Expand errors bar"} placement="top">
      <IconButton className={classes.dropdown} size="small" onClick={() => setExpanded(!expanded)}>
        <Icon>{expanded ? "arrow_drop_down" : "arrow_drop_up"}</Icon>
      </IconButton>
    </Tooltip>
  </Box>;
}

export default ErrorBar;