import Score from "abceditor/lib/Score";
import Duration from "abceditor/lib/Score/Duration";
import Key from "abceditor/lib/Score/Key";
import KeySignatures from "abceditor/lib/Score/Key/KeySignatures";
import Mode from "abceditor/lib/Score/Key/Mode";
import Note from "abceditor/lib/Score/Note";
import Pitch from "abceditor/lib/Score/Pitch";
import Accidental from "abceditor/lib/Score/Pitch/Accidental";
import ScaleValue from "abceditor/lib/Score/Pitch/ScaleValue";

const makePair = (value, text) => ({
  value,
  text
});

export const SPECIES_DATA = [
  makePair(1, "Species 1"),
  makePair(2, "Species 2"),
  makePair(3, "Species 3"),
];

export const MELODY_UPPER_DATA = [
  makePair(true, "Melody upper"),
  makePair(false, "Melody lower"),
];

export const KEY_SIGNATURE_DATA = [
  makePair("Gb", "G♭"),
  makePair("Db", "D♭"),
  makePair("Ab", "A♭"),
  makePair("Eb", "E♭"),
  makePair("Bb", "B♭"),
  makePair("F", "F"),
  makePair("C", "C"),
  makePair("G", "G"),
  makePair("D", "D"),
  makePair("A", "A"),
  makePair("E", "E"),
  makePair("B", "B"),
  makePair("F#", "F#"),
  makePair("C#", "C#"),
];

export const MODE_DATA = [
  makePair("maj", "Ionian"),
  makePair("dor", "Dorian"),
  makePair("phr", "Phrygian"),
  makePair("mix", "Mixolydian"),
  makePair("aeo", "Aeolian"),
];

export const EXAMPLE_MELODIES_DATA = [
  makePair(0, "melody 1"),
  makePair(1, "melody 2"),
  makePair(2, "melody 3"),
  makePair(3, "melody 4"),
];

const score1 = new Score(new Key(KeySignatures.NO_ACCIDENTAL, Mode.MIXOLYDIAN), true);
score1.melody.append(new Note(new Pitch(ScaleValue.G, 3, Accidental.NAT), Duration.WHOLE));
score1.melody.append(new Note(new Pitch(ScaleValue.D, 4, Accidental.NAT), Duration.WHOLE));
score1.melody.append(new Note(new Pitch(ScaleValue.B, 3, Accidental.NAT), Duration.WHOLE));
score1.melody.append(new Note(new Pitch(ScaleValue.E, 4, Accidental.NAT), Duration.WHOLE));
score1.melody.append(new Note(new Pitch(ScaleValue.D, 4, Accidental.NAT), Duration.WHOLE));
score1.melody.append(new Note(new Pitch(ScaleValue.C, 4, Accidental.NAT), Duration.WHOLE));
score1.melody.append(new Note(new Pitch(ScaleValue.B, 3, Accidental.NAT), Duration.WHOLE));
score1.melody.append(new Note(new Pitch(ScaleValue.A, 3, Accidental.NAT), Duration.WHOLE));
score1.melody.append(new Note(new Pitch(ScaleValue.G, 3, Accidental.NAT), Duration.WHOLE));
score1.counterMelody.append(new Note(new Pitch(ScaleValue.G, 3, Accidental.NAT), Duration.WHOLE));
score1.counterMelody.append(new Note(new Pitch(ScaleValue.F, 3, Accidental.NAT), Duration.WHOLE));
score1.counterMelody.append(new Note(new Pitch(ScaleValue.G, 3, Accidental.NAT), Duration.WHOLE));
score1.counterMelody.append(new Note(new Pitch(ScaleValue.E, 3, Accidental.NAT), Duration.WHOLE));
score1.counterMelody.append(new Note(new Pitch(ScaleValue.F, 3, Accidental.NAT), Duration.WHOLE));
score1.counterMelody.append(new Note(new Pitch(ScaleValue.A, 3, Accidental.NAT), Duration.WHOLE));
score1.counterMelody.append(new Note(new Pitch(ScaleValue.G, 3, Accidental.NAT), Duration.WHOLE));
score1.counterMelody.append(new Note(new Pitch(ScaleValue.F, 3, Accidental.SHARP), Duration.WHOLE));
score1.counterMelody.append(new Note(new Pitch(ScaleValue.G, 3, Accidental.NAT), Duration.WHOLE));

const score2 = new Score(new Key(KeySignatures.NO_ACCIDENTAL, Mode.AEOLIAN), false);
score2.melody.append(new Note(new Pitch(ScaleValue.A, 3, Accidental.NAT), Duration.WHOLE));
score2.melody.append(new Note(new Pitch(ScaleValue.E, 3, Accidental.NAT), Duration.WHOLE));
score2.melody.append(new Note(new Pitch(ScaleValue.G, 3, Accidental.NAT), Duration.WHOLE));
score2.melody.append(new Note(new Pitch(ScaleValue.A, 3, Accidental.NAT), Duration.WHOLE));
score2.melody.append(new Note(new Pitch(ScaleValue.B, 3, Accidental.NAT), Duration.WHOLE));
score2.melody.append(new Note(new Pitch(ScaleValue.A, 3, Accidental.NAT), Duration.WHOLE));
score2.melody.append(new Note(new Pitch(ScaleValue.D, 4, Accidental.NAT), Duration.WHOLE));
score2.melody.append(new Note(new Pitch(ScaleValue.C, 4, Accidental.NAT), Duration.WHOLE));
score2.melody.append(new Note(new Pitch(ScaleValue.B, 3, Accidental.NAT), Duration.WHOLE));
score2.melody.append(new Note(new Pitch(ScaleValue.A, 3, Accidental.NAT), Duration.WHOLE));
score2.counterMelody.append(new Note(new Pitch(ScaleValue.A, 4, Accidental.NAT), Duration.WHOLE));
score2.counterMelody.append(new Note(new Pitch(ScaleValue.G, 4, Accidental.NAT), Duration.WHOLE));
score2.counterMelody.append(new Note(new Pitch(ScaleValue.B, 4, Accidental.NAT), Duration.WHOLE));
score2.counterMelody.append(new Note(new Pitch(ScaleValue.A, 4, Accidental.NAT), Duration.WHOLE));
score2.counterMelody.append(new Note(new Pitch(ScaleValue.G, 4, Accidental.NAT), Duration.WHOLE));
score2.counterMelody.append(new Note(new Pitch(ScaleValue.F, 4, Accidental.NAT), Duration.WHOLE));
score2.counterMelody.append(new Note(new Pitch(ScaleValue.F, 4, Accidental.NAT), Duration.WHOLE));
score2.counterMelody.append(new Note(new Pitch(ScaleValue.E, 4, Accidental.NAT), Duration.WHOLE));
score2.counterMelody.append(new Note(new Pitch(ScaleValue.G, 4, Accidental.SHARP), Duration.WHOLE));
score2.counterMelody.append(new Note(new Pitch(ScaleValue.A, 4, Accidental.NAT), Duration.WHOLE));

const score3 = new Score(new Key(KeySignatures.NO_ACCIDENTAL, Mode.DORIAN), false);
score3.melody.append(new Note(new Pitch(ScaleValue.D, 4, Accidental.NAT), Duration.WHOLE));
score3.melody.append(new Note(new Pitch(ScaleValue.E, 4, Accidental.NAT), Duration.WHOLE));
score3.melody.append(new Note(new Pitch(ScaleValue.F, 4, Accidental.NAT), Duration.WHOLE));
score3.melody.append(new Note(new Pitch(ScaleValue.G, 4, Accidental.NAT), Duration.WHOLE));
score3.melody.append(new Note(new Pitch(ScaleValue.A, 4, Accidental.NAT), Duration.WHOLE));
score3.melody.append(new Note(new Pitch(ScaleValue.E, 4, Accidental.NAT), Duration.WHOLE));
score3.melody.append(new Note(new Pitch(ScaleValue.G, 4, Accidental.NAT), Duration.WHOLE));
score3.melody.append(new Note(new Pitch(ScaleValue.F, 4, Accidental.NAT), Duration.WHOLE));
score3.melody.append(new Note(new Pitch(ScaleValue.E, 4, Accidental.NAT), Duration.WHOLE));
score3.melody.append(new Note(new Pitch(ScaleValue.D, 4, Accidental.NAT), Duration.WHOLE));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.D, 5, Accidental.NAT), Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.A, 4, Accidental.NAT), Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.C, 5, Accidental.NAT), Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.E, 5, Accidental.NAT), Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.D, 5, Accidental.NAT), Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.F, 5, Accidental.NAT), Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.E, 5, Accidental.NAT), Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.B, 5, Accidental.NAT), Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.A, 5, Accidental.NAT), Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.E, 5, Accidental.NAT), Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.G, 5, Accidental.NAT), Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.F, 5, Accidental.NAT), Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.E, 5, Accidental.NAT), Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.D, 5, Accidental.NAT), Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.F, 5, Accidental.NAT), Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.A, 4, Accidental.NAT), 
Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.B, 4, Accidental.NAT), 
Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.C, 5, Accidental.SHARP), 
Duration.HALF));
score3.counterMelody.append(new Note(new Pitch(ScaleValue.D, 5, Accidental.NAT), 
Duration.WHOLE));

const score4 = new Score(new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN), true);
score4.melody.append(new Note(new Pitch(ScaleValue.C, 4, Accidental.NAT), Duration.WHOLE));
score4.melody.append(new Note(new Pitch(ScaleValue.D, 4, Accidental.NAT), Duration.WHOLE));
score4.melody.append(new Note(new Pitch(ScaleValue.F, 4, Accidental.NAT), Duration.WHOLE));
score4.melody.append(new Note(new Pitch(ScaleValue.E, 4, Accidental.NAT), Duration.WHOLE));
score4.melody.append(new Note(new Pitch(ScaleValue.F, 4, Accidental.NAT), Duration.WHOLE));
score4.melody.append(new Note(new Pitch(ScaleValue.G, 4, Accidental.NAT), Duration.WHOLE));
score4.melody.append(new Note(new Pitch(ScaleValue.A, 4, Accidental.NAT), Duration.WHOLE));
score4.melody.append(new Note(new Pitch(ScaleValue.G, 4, Accidental.NAT), Duration.WHOLE));
score4.melody.append(new Note(new Pitch(ScaleValue.E, 4, Accidental.NAT), Duration.WHOLE));
score4.melody.append(new Note(new Pitch(ScaleValue.D, 4, Accidental.NAT), Duration.WHOLE));
score4.melody.append(new Note(new Pitch(ScaleValue.C, 4, Accidental.NAT), Duration.WHOLE));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.C, 4, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.B, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.A, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.E, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.F, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.G, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.F, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.E, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.D, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.E, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.D, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.C, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.E, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.G, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.A, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.C, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.D, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.C, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.A, 2, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.C, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.B, 2, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.C, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.E, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.D, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.A, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.G, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.F, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.G, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.B, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.D, 4, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.G, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.A, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.C, 4, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.B, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.G, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.F, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.G, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.E, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.D, 3, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.B, 2, Accidental.NAT), Duration.QUARTER));
score4.counterMelody.append(new Note(new Pitch(ScaleValue.C, 3, Accidental.NAT), Duration.WHOLE));
export const EXAMPLE_MELODIES = [
  {
    species: 1,
    keySignature: "G",
    mode: "mix",
    score: score1,
  },
  {
    species: 1,
    keySignature: "A",
    mode: "aeo",
    score: score2,
  },
  {
    species: 2,
    keySignature: "D",
    mode: "dor",
    score: score3,
  },
  {
    species: 3,
    keySignature: "C",
    mode: "maj",
    score: score4,
  }
];