import Duration from "./Duration";
import Key from "./Key";
import KeySignatures from "./Key/KeySignatures";
import Mode from "./Key/Mode";
import Melody from "./Melody";
import Note from "./Note";
import Pitch from "./Pitch";
import Accidental from "./Pitch/Accidental";
import ScaleValue from "./Pitch/ScaleValue";
import Role from "./Score/Role";

const C4 = new Note(new Pitch(ScaleValue.C, 4, Accidental.NAT), Duration.QUARTER);
const C4whole = new Note(new Pitch(ScaleValue.C, 4, Accidental.NAT), Duration.WHOLE);
const Dsh5 = new Note(new Pitch(ScaleValue.D, 5, Accidental.SHARP), Duration.QUARTER);
const Dshsh5 = new Note(new Pitch(ScaleValue.D, 5, Accidental.DBLSHARP), Duration.QUARTER);
const E4 = new Note(new Pitch(ScaleValue.E, 4, Accidental.NAT), Duration.QUARTER);

test("key", () => {
  const startEmpty: Melody = new Melody([], new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN));
  expect(startEmpty.key).toStrictEqual(new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN))
  startEmpty.key = new Key(KeySignatures.NO_ACCIDENTAL, Mode.AEOLIAN)
  expect(startEmpty.key).toStrictEqual(new Key(KeySignatures.NO_ACCIDENTAL, Mode.AEOLIAN))
})

test("getNote", () => {
  const startWithNotes: Melody = new Melody([C4, Dsh5, Dshsh5, E4], new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN));
  expect(startWithNotes.getNote(0)).toStrictEqual(C4)
  expect(startWithNotes.getNote(2)).toStrictEqual(Dshsh5)
  expect(startWithNotes.getNote(5)).toBe(undefined)
  startWithNotes.append(C4)
  expect(startWithNotes.getNote(4)).toStrictEqual(C4)
})

test("append, insert", () => {
  const startEmpty: Melody = new Melody([], new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN));
  startEmpty.append(C4)
  startEmpty.append(C4)
  startEmpty.append(C4)
  expect(startEmpty.getNote(0)).toStrictEqual(C4)
  expect(startEmpty.getNote(1)).toStrictEqual(C4)
  expect(startEmpty.getNote(2)).toStrictEqual(C4)
  expect(startEmpty.getNote(3)).toBe(undefined)
  startEmpty.insert(Dsh5, 1)
  expect(startEmpty.getNote(1)).toStrictEqual(Dsh5)
  startEmpty.insert(Dsh5, 3)
  expect(startEmpty.getNote(3)).toBe(Dsh5)
  expect(startEmpty.getNote(4)).toBe(C4)
});

test('length', () => {
  const twoNotes: Melody = new Melody([C4, Dsh5], new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN));
  const Eshsh6whole = new Note(new Pitch(ScaleValue.E, 6, Accidental.DBLSHARP), Duration.WHOLE);
  const melodyWithTie: Melody = new Melody([C4, Dsh5, Dshsh5, Eshsh6whole], new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN));
  expect(twoNotes.length()).toBe(2);
  expect(melodyWithTie.length()).toBe(7);
})

test('delete', () => {
  const twoNotes: Melody = new Melody([C4, Dsh5], new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN));
  twoNotes.delete(0);
  expect(twoNotes.notes.length).toBe(1);
  expect(twoNotes.getNote(0)).toStrictEqual(Dsh5);
})

test('fromJSON', () => {
  const oneNote: Melody = new Melody([C4], new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN));
  expect(Melody.fromJSON(JSON.parse('{"notes":[{"pitch":{"scaleValue":0,"octave":4,"accidental":0},"duration":1.0,"rest":false}],"key":{"keySignature":{"accidentals":[0,0,0,0,0,0,0],"ionianTonic":0},"mode":0},"unitBeat":1.0}'))).toStrictEqual(oneNote);
})

test("getABCMapping", () => {
  const oneNote: Melody = new Melody([C4], new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN));
  const startEmpty: Melody = new Melody([], new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN));
  const startWithNotes: Melody = new Melody([C4, Dsh5, Dshsh5, E4], new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN));
  const Eshsh6whole = new Note(new Pitch(ScaleValue.E, 6, Accidental.DBLSHARP), Duration.WHOLE);
  const melodyWithTie: Melody = new Melody([C4, Dsh5, Dshsh5, Eshsh6whole], new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN));
  const modeChangeisSame: Melody = new Melody([C4, Dsh5, Dshsh5, Eshsh6whole], new Key(KeySignatures.NO_ACCIDENTAL, Mode.PHRYRGIAN));
  const keyInsideNotes: Melody = new Melody([C4, Dsh5, Dshsh5, Eshsh6whole], new Key(KeySignatures.ONE_SHARP, Mode.DORIAN));
  const newLine: Melody = new Melody(
    [C4whole,C4whole,C4whole,C4whole,C4whole,C4whole,C4whole,C4,C4,C4,C4whole], 
    new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN))

  expect(startEmpty.getABCMapping(Role.MELODY, 0)).toStrictEqual(["[V:M]|", new Map()])
  expect(oneNote.getABCMapping(Role.COUNTERMELODY, 0)).toStrictEqual(["[V:CM]C2|", new Map([[6, 0]])])
  expect(startWithNotes.getABCMapping(Role.MELODY, 200)).toStrictEqual(
    ["[V:M]C2^D'2^^D'2E2||", 
    new Map([[205, 0],[207, 1],[211, 2],[216, 3]])])
  expect(melodyWithTie.getABCMapping(Role.MELODY, 0)).toStrictEqual(
    ["[V:M]C2^D'2^^D'2^^E''2-|^^E''6|", 
    new Map([[5, 0],[7, 1],[11, 2],[16, 3],[24, 3]])])
  expect(modeChangeisSame.getABCMapping(Role.MELODY, 0)).toStrictEqual(
    ["[V:M]C2^D'2^^D'2^^E''2-|^^E''6|", 
    new Map([[5, 0],[7, 1],[11, 2],[16, 3],[24, 3]])])
  expect(keyInsideNotes.getABCMapping(Role.MELODY, 0)).toStrictEqual(
    ["[V:M]C2^D'2^^D'2^^E''2-|^^E''6|", 
    new Map([[5, 0],[7, 1],[11, 2],[16, 3],[24, 3]])])
  expect(newLine.getABCMapping(Role.MELODY, 0)).toStrictEqual(
    ["[V:M]C8|C8|C8|C8|C8|C8|C8|C2C2C2C2-|\n[V:M]C6|", 
    new Map([[5, 0],[8, 1],[11, 2],[14, 3],[17, 4],[20, 5],[23, 6],[26, 7],[28, 8],[30, 9],[32, 10],[42, 10]])])
})