import { FormControl, makeStyles, MenuItem, Select } from "@material-ui/core";
import React from "react";

const useStyles = makeStyles({
  root: {
    marginRight: 5,
  }
})

const Dropdown = ({value, onChange, data}) => {
  const classes = useStyles();
  return <FormControl className={classes.root}>
    <Select
      displayEmpty
      value={value}
      inputProps={{ 'aria-label': 'Without label' }}
      onChange={onChange}
      disableUnderline
    >
      { data.map(datum => 
        <MenuItem key={datum.value} value={datum.value}>{datum.text}</MenuItem>
      )}
    </Select>
  </FormControl>;
}

export default Dropdown;