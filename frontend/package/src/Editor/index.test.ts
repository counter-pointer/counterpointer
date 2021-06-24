import Editor from "."
import Score from "../Score"
import Duration from "../Score/Duration";
import Key from "../Score/Key"
import KeySignatures from "../Score/Key/KeySignatures"
import Mode from "../Score/Key/Mode";
import Note from "../Score/Note";
import Pitch from "../Score/Pitch";
import Accidental from "../Score/Pitch/Accidental";
import ScaleValue from "../Score/Pitch/ScaleValue";
import Role from "../Score/Score/Role";
import Species from "./Species";

test('constructor', () => {
  const cAbove = new Editor(new Score(new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN), true), Species.FIRST);
  const gBelow = new Editor(new Score(new Key(KeySignatures.ONE_SHARP, Mode.IONIAN), false), Species.SECOND);
  cAbove.selectNote(Role.MELODY, 0);
  expect(cAbove.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.C, 4, Accidental.NAT), Duration.WHOLE));
  cAbove.selectNote(Role.COUNTERMELODY, 0);
  expect(cAbove.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.C, 3, Accidental.NAT), Duration.WHOLE));

  gBelow.selectNote(Role.MELODY, 0);
  expect(gBelow.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.G, 4, Accidental.NAT), Duration.HALF));
  gBelow.selectNote(Role.COUNTERMELODY, 0);
  expect(gBelow.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.G, 5, Accidental.NAT), Duration.HALF));
})

test('raiseSelectedNote', () => {
  const cAbove = new Editor(new Score(new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN), true), Species.FIRST);
  cAbove.selectNote(Role.MELODY, 0);
  cAbove.raiseSelectedNote();
  expect(cAbove.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.D, 4, Accidental.NAT), Duration.WHOLE));
  cAbove.raiseSelectedNote();
  expect(cAbove.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.E, 4, Accidental.NAT), Duration.WHOLE));
});

test('lowerSelectedNote', () => {
  const cAbove = new Editor(new Score(new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN), true), Species.FIRST);
  cAbove.selectNote(Role.COUNTERMELODY, 0);
  cAbove.lowerSelectedNote();
  expect(cAbove.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.B, 2, Accidental.NAT), Duration.WHOLE));
  cAbove.lowerSelectedNote();
  expect(cAbove.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.A, 2, Accidental.NAT), Duration.WHOLE));
});

test('sharpSelectedNote', () => {
  const cAbove = new Editor(new Score(new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN), true), Species.FIRST);
  cAbove.selectNote(Role.MELODY, 0);
  cAbove.sharpSelectedNote();
  expect(cAbove.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.C, 4, Accidental.SHARP), Duration.WHOLE));
  cAbove.sharpSelectedNote();
  expect(cAbove.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.C, 4, Accidental.DBLSHARP), Duration.WHOLE));
});

test('flatSelectedNote', () => {
  const cAbove = new Editor(new Score(new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN), true), Species.FIRST);
  cAbove.selectNote(Role.COUNTERMELODY, 0);
  cAbove.flatSelectedNote();
  expect(cAbove.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.C, 3, Accidental.FLAT), Duration.WHOLE));
  cAbove.flatSelectedNote();
  expect(cAbove.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.C, 3, Accidental.DBLFLAT), Duration.WHOLE));
});

test('raiseOctaveSelectedNote', () => {
  const gBelow = new Editor(new Score(new Key(KeySignatures.ONE_SHARP, Mode.IONIAN), false), Species.SECOND);
  gBelow.selectNote(Role.MELODY, 0);
  gBelow.raiseOctaveSelectedNote();
  expect(gBelow.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.G, 5, Accidental.NAT), Duration.HALF));
  gBelow.raiseOctaveSelectedNote();
  expect(gBelow.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.G, 6, Accidental.NAT), Duration.HALF));
});

test('lowerOctaveSelectedNote', () => {
  const gBelow = new Editor(new Score(new Key(KeySignatures.ONE_SHARP, Mode.IONIAN), false), Species.SECOND);
  gBelow.selectNote(Role.COUNTERMELODY, 0);
  gBelow.lowerOctaveSelectedNote();
  expect(gBelow.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.G, 4, Accidental.NAT), Duration.HALF));
  gBelow.lowerOctaveSelectedNote();
  expect(gBelow.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.G, 3, Accidental.NAT), Duration.HALF));
});

test('changeDurationSelectedNote', () => {
  const gBelow = new Editor(new Score(new Key(KeySignatures.ONE_SHARP, Mode.IONIAN), false), Species.SECOND);
  gBelow.selectNote(Role.COUNTERMELODY, 0);
  gBelow.changeDurationSelectedNote(Duration.WHOLE);
  expect(gBelow.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.G, 5, Accidental.NAT), Duration.WHOLE));
  gBelow.changeDurationSelectedNote(Duration.QUARTER);
  expect(gBelow.getSelectedNote()).toStrictEqual(new Note(new Pitch(ScaleValue.G, 5, Accidental.NAT), Duration.QUARTER));
});
