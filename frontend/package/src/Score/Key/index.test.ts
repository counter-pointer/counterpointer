import Duration from "../Duration";
import Note from "../Note";
import Pitch from "../Pitch";
import Accidental from "../Pitch/Accidental";
import ScaleValue from "../Pitch/ScaleValue";
import Key from "./index";
import KeySignatures from "./KeySignatures";
import Mode from "./Mode";

const cmajor: Key = new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN);
const gmajor: Key = new Key(KeySignatures.ONE_SHARP, Mode.IONIAN);
const aminor: Key = new Key(KeySignatures.NO_ACCIDENTAL, Mode.AEOLIAN);
const csharpmaj: Key = new Key(KeySignatures.SEVEN_SHARP, Mode.IONIAN);
const cdorian: Key = new Key(KeySignatures.TWO_FLAT, Mode.DORIAN);
const asharpmin: Key = new Key(KeySignatures.SEVEN_SHARP, Mode.AEOLIAN);
const gsharpmix: Key = new Key(KeySignatures.SEVEN_SHARP, Mode.MIXOLYDIAN);

test("keySignature", () => {
  expect(cmajor.keySignature).toBe(KeySignatures.NO_ACCIDENTAL);
});

test("mode", () => {
  expect(cmajor.mode).toBe(Mode.IONIAN);
});

test("isInKey", () => {
  expect(cmajor.isPitchInKey(
    new Pitch(ScaleValue.C, 0, Accidental.NAT))).toBe(true);
  expect(cmajor.isPitchInKey(
    new Pitch(ScaleValue.D, 10, Accidental.NAT))).toBe(true);
  expect(cmajor.isPitchInKey(
    new Pitch(ScaleValue.C, 0, Accidental.SHARP))).toBe(false);
  expect(csharpmaj.isPitchInKey(
    new Pitch(ScaleValue.C, 0, Accidental.SHARP))).toBe(true);
  expect(asharpmin.isPitchInKey(
    new Pitch(ScaleValue.C, 0, Accidental.SHARP))).toBe(true);
  //and also testing if NoteInKey has same logic..
  expect(cmajor.isNoteInKey(
    new Note(new Pitch(ScaleValue.C, 0, Accidental.NAT),Duration.HALF))).toBe(true);
});

test("isLeadingTone", () => {
  expect(cmajor.isPitchLeadingTone(
    new Pitch(ScaleValue.B, 0, Accidental.NAT))).toBe(true);
  expect(cmajor.isPitchLeadingTone(
    new Pitch(ScaleValue.B, 0, Accidental.SHARP))).toBe(true);
  expect(cmajor.isPitchLeadingTone(
    new Pitch(ScaleValue.B, 0, Accidental.DBLFLAT))).toBe(true);
  expect(cmajor.isPitchLeadingTone(
    new Pitch(ScaleValue.B, 999, Accidental.DBLFLAT))).toBe(true);
  expect(aminor.isPitchLeadingTone(
    new Pitch(ScaleValue.B, 0, Accidental.NAT))).toBe(false);
  expect(aminor.isPitchLeadingTone(
    new Pitch(ScaleValue.G, 0, Accidental.NAT))).toBe(true);
  //
  expect(cmajor.isNoteLeadingTone(
    new Note(new Pitch(ScaleValue.B, 0, Accidental.NAT), Duration.QUARTER)
  )).toBe(true)
});

test('accidentalFromScaleValue', () => {
  expect(cmajor.accidentalFromScaleValue(ScaleValue.D)).toBe(Accidental.NAT);
  expect(aminor.accidentalFromScaleValue(ScaleValue.A)).toBe(Accidental.NAT);
  expect(aminor.accidentalFromScaleValue(ScaleValue.B)).toBe(Accidental.NAT);
  expect(aminor.accidentalFromScaleValue(ScaleValue.C)).toBe(Accidental.NAT);
  expect(aminor.accidentalFromScaleValue(ScaleValue.D)).toBe(Accidental.NAT);
  expect(aminor.accidentalFromScaleValue(ScaleValue.E)).toBe(Accidental.NAT);
  expect(aminor.accidentalFromScaleValue(ScaleValue.F)).toBe(Accidental.NAT);
  expect(aminor.accidentalFromScaleValue(ScaleValue.G)).toBe(Accidental.NAT);
  
  expect(cdorian.accidentalFromScaleValue(ScaleValue.C)).toBe(Accidental.NAT);
  expect(cdorian.accidentalFromScaleValue(ScaleValue.D)).toBe(Accidental.NAT);
  expect(cdorian.accidentalFromScaleValue(ScaleValue.E)).toBe(Accidental.FLAT);
  expect(cdorian.accidentalFromScaleValue(ScaleValue.F)).toBe(Accidental.NAT);
  expect(cdorian.accidentalFromScaleValue(ScaleValue.G)).toBe(Accidental.NAT);
  expect(cdorian.accidentalFromScaleValue(ScaleValue.A)).toBe(Accidental.NAT);
  expect(cdorian.accidentalFromScaleValue(ScaleValue.B)).toBe(Accidental.FLAT);
  
  expect(csharpmaj.accidentalFromScaleValue(ScaleValue.C)).toBe(Accidental.SHARP);
  expect(csharpmaj.accidentalFromScaleValue(ScaleValue.B)).toBe(Accidental.SHARP);
})

//TODO: test 'addAccidentaltoKey()'

test('toString', () => {
  expect(cmajor.toString()).toBe('Cmaj');
  expect(aminor.toString()).toBe('Aaeo');
  expect(csharpmaj.toString()).toBe('C#maj');
  expect(asharpmin.toString()).toBe('A#aeo');
  expect(gsharpmix.toString()).toBe('G#mix');
  expect(gmajor.toString()).toBe('Gmaj')
})

test('fromString', () => {
  expect(Key.fromString('Cmaj').toString()).toStrictEqual(cmajor.toString());
  expect(Key.fromString('Aaeo').toString()).toStrictEqual(aminor.toString());
  expect(Key.fromString('C#maj').toString()).toStrictEqual(csharpmaj.toString());
  expect(Key.fromString('A#aeo').toString()).toStrictEqual(asharpmin.toString());
  expect(Key.fromString('G#mix').toString()).toStrictEqual(gsharpmix.toString());
  expect(Key.fromString('Gmaj').toString()).toStrictEqual(gmajor.toString());
})

test('fromJSON', () => {
  expect(Key.fromJSON(JSON.parse('{"keySignature":{"accidentals":[0,0,0,0,0,0,0],"ionianTonic":0},"mode":0}'))).toStrictEqual(cmajor);
})