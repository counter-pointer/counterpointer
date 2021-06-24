import Duration from "./Duration"
import Key from "./Key"
import KeySignatures from "./Key/KeySignatures"
import Mode from "./Key/Mode"
import Note from "./Note"
import Pitch from "./Pitch"
import Accidental from "./Pitch/Accidental"
import ScaleValue from "./Pitch/ScaleValue"


test("pitch", () => {
  const C4 = new Note(new Pitch(ScaleValue.C, 4, Accidental.NAT), Duration.QUARTER);
  const Dsh5 = new Note(new Pitch(ScaleValue.D, 5, Accidental.SHARP), Duration.QUARTER);
  const Dshsh5 = new Note(new Pitch(ScaleValue.D, 5, Accidental.DBLSHARP), Duration.QUARTER);
  
  expect(C4.pitch).toStrictEqual(new Pitch(ScaleValue.C, 4, Accidental.NAT))
  expect(Dsh5.pitch).toStrictEqual(new Pitch(ScaleValue.D, 5, Accidental.SHARP))
  expect(Dshsh5.pitch).toStrictEqual(new Pitch(ScaleValue.D, 5, Accidental.DBLSHARP))
})

test("isRest", () => {
  const Dsh5 = new Note(new Pitch(ScaleValue.D, 5, Accidental.SHARP), Duration.QUARTER);
  expect(Dsh5.isRest()).toBe(false);
  Dsh5.pitch = null;
  expect(Dsh5.isRest()).toBe(true);
})

test('fromJSON', () => {
  const wholeRest = new Note(null, Duration.WHOLE);
  const C4 = new Note(new Pitch(ScaleValue.C, 4, Accidental.NAT), Duration.QUARTER);
  expect(Note.fromJSON(JSON.parse('{"pitch":null,"duration":4}'))).toStrictEqual(wholeRest);
  expect(Note.fromJSON(JSON.parse('{"pitch":{"scaleValue":0,"octave":4,"accidental":0},"duration":1}'))).toStrictEqual(C4);
})

test("toABC", () => {
  const C4 = new Note(new Pitch(ScaleValue.C, 4, Accidental.NAT), Duration.QUARTER);
  const Dsh5 = new Note(new Pitch(ScaleValue.D, 5, Accidental.SHARP), Duration.HALF);
  const Dshsh5 = new Note(new Pitch(ScaleValue.D, 5, Accidental.DBLSHARP), Duration.WHOLE);
  const Eflat0 = new Note(new Pitch(ScaleValue.E, 0, Accidental.FLAT), Duration.WHOLE)
  const CMaj: Key = new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN);
  const DMaj: Key = new Key(KeySignatures.TWO_SHARP, Mode.IONIAN);
  const wholeRest = new Note(null, Duration.WHOLE);
  expect(C4.toABC(CMaj)).toBe("C2");
  expect(C4.toABC(DMaj)).toBe("=C2");
  expect(Dsh5.toABC(CMaj)).toBe("^D'4");
  expect(Dshsh5.toABC(DMaj)).toBe("^^D'8");
  expect(wholeRest.toABC(CMaj)).toBe("z8")
  expect(Eflat0.toABC(CMaj)).toBe("_E,,,,8")
})