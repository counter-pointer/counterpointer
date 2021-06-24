import { makeStyles } from "@material-ui/core";
import Alert from '@material-ui/lab/Alert';
import React, { useEffect } from "react";

const useStyles = makeStyles({
  root: {
    position: 'fixed',
    top: 20,
    left: '50%',
    transform: 'translateX(-50%)',
    zIndex: 10000,
  },
});

const WarningBanner = ({message, reset, timeout=2000}) => {
  const classes = useStyles();
  useEffect(() => {
    setTimeout(() => {
      reset();
    }, timeout);
  });

  return <Alert severity="error" className={classes.root}>{message}</Alert>;
}

export default WarningBanner;