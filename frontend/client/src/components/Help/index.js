import { AppBar, Box, Dialog, makeStyles, Tab, Tabs } from "@material-ui/core";
import React from "react";
import About from "./About";
import HotKeys from "./HotKeys";

const useStyles = makeStyles({
  tab: {
    position: "relative",
    height: "80vh",
    overflowY: "auto",
  },
})

const TabPanel = ({children, value, index, ...other}) => {
  return <Box
    role="tabpanel"
    hidden={value !== index}
    p={3}
    {...other}
  >
    {value === index && children}
  </Box>;
}

const Help = ({show, setShow}) => {
  const classes = useStyles();
  const [tab, setTab] = React.useState(0);
  return <Dialog maxWidth="lg" fullWidth open={show} onClose={() => setShow(false)}>
    <AppBar position="static">
      <Tabs value={tab} onChange={(e, v) => setTab(v)}>
        <Tab label="About"/>
        <Tab label="Hotkeys"/>
      </Tabs>
    </AppBar>
    <TabPanel className={classes.tab} value={tab} index={0}>
      <About/>
    </TabPanel>
    <TabPanel className={classes.tab} value={tab} index={1}>
      <HotKeys/>
    </TabPanel>
  </Dialog>
}

export default Help;