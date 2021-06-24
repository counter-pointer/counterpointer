import { Button, Menu, MenuItem, Tooltip } from "@material-ui/core";
import React, { useState } from "react";

const Clear = ({clearMelody, clearCounterMelody}) => {
  const [anchorEl, setAnchorEl] = useState(null);
  const handleClick = (e) => {
    setAnchorEl(e.currentTarget);
  }
  const handleClose = () => {
    setAnchorEl(null);
  }

  const handleClearMelody = () => {
    clearMelody();
    handleClose();
  }

  const handleClearCounterMelody = () => {
    clearCounterMelody();
    handleClose();
  }

  const handleClearBoth = () => {
    clearMelody();
    clearCounterMelody();
    handleClose();
  }

  return <>
    <Tooltip title="Resets the melody/countermelody">
      <Button onClick={handleClick}>
        Clear
      </Button>
    </Tooltip>
    <Menu
      anchorEl={anchorEl}
      keepMounted
      open={Boolean(anchorEl)}
      onClose={handleClose}
    >
      <MenuItem onClick={handleClearMelody}>Clear melody</MenuItem>
      <MenuItem onClick={handleClearCounterMelody}>Clear countermelody</MenuItem>
      <MenuItem onClick={handleClearBoth}>Clear both</MenuItem>
    </Menu>
  </>;
}

export default Clear;