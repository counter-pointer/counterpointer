[![logo](frontend/client/logo/logo_text_color.png)](https://www.counterpointer.app/)

![CI Status](https://github.com/counter-pointer/counterpointer/actions/workflows/ci.yml/badge.svg)

---

## 🎼 CounterPointer 🎼

CounterPointer is a teaching tool to help music theory students learn more about *[species counterpoint](https://en.wikipedia.org/wiki/Counterpoint)*. You can use the web editor to input your melody, see generated example countermelodies, or write your own countermelody and check it against the set of counterpoint rules, all while being able to listen to your score played back on a piano in real time. Instead of manually checking for rule violations, you can immediately get feedback on your countermelody and quickly correct any mistakes.

## 🔥 Motivation

CounterPointer was created to be a tool for music theory students / instructors to use to enrich the classroom learning experience of species counterpoint. In our experience, we have found counterpoint homework to be quite often rote, requiring very little skill besides following a long checklist of rules. We thought it would be an interesting take to see how the strong mesh of rules in counterpoint could lend to an algorithm, and hope that others may find a service like this novel and helpful!

## ⚙️️ Features

- ### CHECKING

  - Input a melody, countermelody duo and check its validity against a given species of rules.
  - Rule violations show up at the bottom of the screen and give a small description on how a counterpoint rule was violated.

- ### GENERATION

  - Input a melody and the generate button can generate a random valid countermelody to fit the counterpoint rules of the species, key, and mode specified.

- ### PLAYBACK AND DOWNLOAD

  - Press the play button to see how your counterpoint sounds at a fixed tempo, and press the download button to get a PDF version of the score made! 

## 🔌 Installation

This project is 24/7 hosted at the link given above, but if you want to run it yourself:

- BACKEND
  - *Our backend runs on Maven for Java. The download for Maven can be found [here](https://maven.apache.org/download.cgi)*
  - *Once downloaded*, navigate into the ```/server``` folder, run ```mvn package``` to build the project, and ```./run``` to start the server on localhost.
- FRONTEND
  - *Our frontend runs on Typescript and React.js. Typescript can be found here, and React (along with all other dependencies) can be installed by running ```npm install``` in the ```/frontend``` directory*
  - *To build the frontend typescript package, navigate into the ```/frontend/package``` directory and run ```tsc build```.*
  - *Once the typescript packages is built, navigate to the ```/frontend/client``` directory and run ```npm start``` or ```yarn start``` to initiate the React app on localhost*

## ❓ How to use?

Highlight a region of the staff and place notes in with the respective letter keys on your keyboard. You can use the dropdowns to modify the key, mode, and species s as well as, once inputting is finished, check or generate a counterpoint. Further controls and hotkeys *(for accidentals, note duration, etc.)*  are detailed on the landing screen of the site.

## 🎵 Where can I learn more about species counterpoint?

We encourage you to check out Robert Hutchinson's [online textbook](https://musictheory.pugetsound.edu/mt21c/SpeciesCounterpoint.html) to species counterpoint. It lays out all the rules we are checking for in each species. If you need a refresher on basic music theory, [this section](https://musictheory.pugetsound.edu/mt21c/Pitch.html) of the same online textbook is the right place to be.

## I love this ❤️! How can I show my appreciation?

Star the project, and help us make it better by opening issues and pull requests!

## 👋 Credits

CounterPointer was created as the final project for Brown University's [CSCI 0320](http://cs.brown.edu/courses/cs0320/) course by [Alex Ding](https://github.com/alexander-ding), [En-Hua Holtz](https://github.com/0x85FB9C51), [John Chung](https://github.com/johnchung1010), and [Orion Bloomfield](https://github.com/obloomfield).
