import { Button, Menu, MenuItem, Tooltip } from "@material-ui/core";
import React, { useState } from "react";
import { EXAMPLE_MELODIES, EXAMPLE_MELODIES_DATA } from "./data";

const Load = ({load}) => {
  const [anchorEl, setAnchorEl] = useState(null);
  const handleClick = (e) => {
    setAnchorEl(e.currentTarget);
  }
  const handleClose = () => {
    setAnchorEl(null);
  }

  const handleLoad = (value) => {
    load(EXAMPLE_MELODIES[value]);
    handleClose();
  }
  
  return <>
    <Tooltip title="Load an example score">
      <Button onClick={handleClick}>
        Load
      </Button>
    </Tooltip>
    <Menu
      anchorEl={anchorEl}
      keepMounted
      open={Boolean(anchorEl)}
      onClose={handleClose}
    >
      { 
        EXAMPLE_MELODIES_DATA.map(v => 
          <MenuItem key={v.value} onClick={() => handleLoad(v.value)}>Load {v.text}</MenuItem>
        ) 
      }
    </Menu>
  </>;
}

export default Load;