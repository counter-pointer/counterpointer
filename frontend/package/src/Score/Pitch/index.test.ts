import Pitch from ".";
import Key from "../Key";
import KeySignatures from "../Key/KeySignatures";
import Mode from "../Key/Mode";
import Accidental from "./Accidental";
import ScaleValue from "./ScaleValue";



test('toString', () => {
  const C4: Pitch = new Pitch(ScaleValue.C, 4, Accidental.NAT);
  const Abb5: Pitch = new Pitch(ScaleValue.A, 5, Accidental.DBLFLAT);
  
  expect(C4.toString()).toBe('C4');
  expect(Abb5.toString()).toBe('A♭♭5');
})

test('raise', () => {
  const C4: Pitch = new Pitch(ScaleValue.C, 4, Accidental.NAT);
  const C5: Pitch = new Pitch(ScaleValue.C, 5, Accidental.NAT);


  expect(Pitch.raise(C4)).toStrictEqual(new Pitch(ScaleValue.D, 4, Accidental.NAT));
  expect(Pitch.raise(new Pitch(ScaleValue.B, 4, Accidental.NAT))).toStrictEqual(new Pitch(ScaleValue.C, 5, Accidental.NAT));
});

test('lower', () => {
  const C4: Pitch = new Pitch(ScaleValue.C, 4, Accidental.NAT);

  expect(Pitch.lower(C4)).toStrictEqual(new Pitch(ScaleValue.B, 3, Accidental.NAT));
  expect(Pitch.lower(new Pitch(ScaleValue.B, 4, Accidental.NAT))).toStrictEqual(new Pitch(ScaleValue.A, 4, Accidental.NAT));
});

test("scaleValue", () => {
  const C4: Pitch = new Pitch(ScaleValue.C, 4, Accidental.NAT);
  const C5: Pitch = new Pitch(ScaleValue.C, 5, Accidental.NAT);

  expect(C4.scaleValue).toEqual(ScaleValue.C);
  expect(C5.scaleValue).toEqual(ScaleValue.C);

  C4.scaleValue = ScaleValue.G
  expect(C4.scaleValue).toEqual(ScaleValue.G);
});

test("octave", () => {
  const C4: Pitch = new Pitch(ScaleValue.C, 4, Accidental.NAT);
  const C5: Pitch = new Pitch(ScaleValue.C, 5, Accidental.NAT);
  C4.octave = 24;

  expect(C4.octave).toEqual(24);
  expect(C5.octave).toEqual(5);
});

test("accidental", () => {
  const C4: Pitch = new Pitch(ScaleValue.C, 4, Accidental.NAT);
  const C5: Pitch = new Pitch(ScaleValue.C, 5, Accidental.NAT);
  const Abb5: Pitch = new Pitch(ScaleValue.A, 5, Accidental.DBLFLAT);
  expect(C4.accidental).toEqual(Accidental.NAT);
  expect(Abb5.accidental).toEqual(Accidental.DBLFLAT);

  C5.accidental = Accidental.SHARP
  expect(C5.accidental).toEqual(Accidental.SHARP);
});

test('fromJSON', () => {
  const C5: Pitch = new Pitch(ScaleValue.C, 5, Accidental.NAT);
  expect(Pitch.fromJSON(JSON.parse('{"scaleValue":0,"octave":5,"accidental":0}'))).toStrictEqual(C5);
})

test("toABC", () => {
  const C4: Pitch = new Pitch(ScaleValue.C, 4, Accidental.NAT);
  const Csharp5: Pitch = new Pitch(ScaleValue.C, 5, Accidental.SHARP);
  const Dflat0: Pitch = new Pitch(ScaleValue.D, 0, Accidental.FLAT)
  const CMaj: Key = new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN);
  const DMaj: Key = new Key(KeySignatures.TWO_SHARP, Mode.IONIAN);
  expect(C4.toABC(CMaj)).toBe("C");
  expect(C4.toABC(DMaj)).toBe("=C");
  expect(Csharp5.toABC(CMaj)).toBe("^C'");
  expect(Csharp5.toABC(DMaj)).toBe("^C'");
  expect(Dflat0.toABC(CMaj)).toBe("_D,,,,")
})