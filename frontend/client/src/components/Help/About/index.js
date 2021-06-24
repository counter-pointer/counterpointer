import { Box, createMuiTheme, Link, makeStyles, ThemeProvider, Typography } from "@material-ui/core";
import React from "react";
import GithubCorner from 'react-github-corner';

const theme = createMuiTheme();

const useStyles = makeStyles({
  section: {
    marginBottom: "8px",
  }
})

const About = () => {
  const classes = useStyles();
  return <ThemeProvider theme={theme}>
    <Box>
      <Box className={classes.section}>
        <Typography variant="h6">Welcome to CounterPointer!</Typography>
        <Typography variant="body1">
          CounterPointer is a teaching tool to help music theory students learn more about species counterpoint. You can use the web editor to input your melody, see generated example countermelodies, or write your own countermelody and check it against the set of counterpoint rules, all while being able to listen to your score played back on a piano in real time. Instead of manually checking for rule violations, you can immediately get feedback on your countermelody and quickly correct any mistakes.
        </Typography>
        <Typography variant="body1">
          To get started, use the <Typography variant="button">load</Typography> button to load an example score in to play around with. Check out the <Typography variant="button">hotkeys</Typography> tab to see how to use the score editor. Remember, you can always bring up this welcome screen by clicking the <Typography variant="button">help</Typography> button. The alto clef is always the melody line, and the other clef is the countermelody line.
        </Typography>
      </Box>
      <Box className={classes.section}>
        <Typography variant="h6">Where can I learn more about species counterpoint 🎵?</Typography>
        <Typography variant="body1">
          We encourage you to check out Robert Hutchinson's <Link href="https://musictheory.pugetsound.edu/mt21c/SpeciesCounterpoint.html" target="_blank" rel="noopener">online textbook</Link> to species counterpoint. It lays out all the rules we are checking for in each species.
        </Typography>
        <Typography variant="body1">
          If you need a refresher on basic music theory, this <Link href="https://musictheory.pugetsound.edu/mt21c/Pitch.html" target="_blank" rel="noopener">section</Link> of the same online textbook is the right place to be.
        </Typography>
      </Box>
      <Box className={classes.section}>
        <Typography variant="h6">How was CounterPointer made?</Typography>
        <Typography variant="body1">
          CounterPointer was created as the final project for Brown University's <Link href="http://cs.brown.edu/courses/cs0320/" target="_blank" rel="noopener">CSCI 0320</Link> course by <Link href="https://github.com/alexander-ding" target="_blank" rel="noopener">Alex Ding</Link>, <Link href="https://github.com/0x85FB9C51" target="_blank" rel="noopener">En-Hua Holtz</Link>, <Link href="https://github.com/johnchung1010" target="_blank" rel="noopener">John Chung</Link>, and <Link href="https://github.com/obloomfield" target="_blank" rel="noopener">Orion Bloomfield</Link>.
        </Typography>
      </Box>
      <Box className={classes.section}>
        <Typography variant="h6">I love this ❤️! How can I show my appreciation?</Typography>
        <Typography variant="body1">
          Allow me to redirect your attention to the cat on the top right corner. Click on the cat to visit our GitHub repository, star the project, and help us make it better by opening issues and pull requests!
        </Typography>
      </Box>
      <GithubCorner href="https://github.com/counter-pointer/counterpointer" />
    </Box>
  </ThemeProvider>;
}

export default About;