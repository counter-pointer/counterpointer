import Accidental from "../Pitch/Accidental";
import ScaleValue from "../Pitch/ScaleValue";
import KeySignature from "./KeySignature";
import KeySignatures from "./KeySignatures";
import Mode from "./Mode";

test("accidentals", () => {
  expect(KeySignatures.NO_ACCIDENTAL.accidentals).toStrictEqual([Accidental.NAT,Accidental.NAT,Accidental.NAT,Accidental.NAT,Accidental.NAT,Accidental.NAT,Accidental.NAT]);
  expect(KeySignatures.FOUR_SHARP.accidentals).toStrictEqual([Accidental.NAT, Accidental.SHARP, Accidental.SHARP, Accidental.NAT, Accidental.NAT , Accidental.SHARP, Accidental.SHARP]);
});

test("ionianTonic", () => {
  expect(KeySignatures.NO_ACCIDENTAL.ionianTonic).toEqual(ScaleValue.C)
  expect(KeySignatures.ONE_FLAT.ionianTonic).toEqual(ScaleValue.F)
});

test("getScaleLetterNames", () => {
  expect(KeySignatures.NO_ACCIDENTAL.getScaleLetterNames(ScaleValue.C)).toStrictEqual(
    [ScaleValue.C, ScaleValue.D, ScaleValue.E, ScaleValue.F, ScaleValue.G, ScaleValue.A, ScaleValue.B]
  );
  expect(KeySignatures.NO_ACCIDENTAL.getScaleLetterNames(ScaleValue.F)).toStrictEqual(
    [ScaleValue.F, ScaleValue.G, ScaleValue.A, ScaleValue.B, ScaleValue.C, ScaleValue.D, ScaleValue.E]
  );
  expect(KeySignatures.SEVEN_FLAT.getScaleLetterNames(ScaleValue.F)).toStrictEqual(
    [ScaleValue.F, ScaleValue.G, ScaleValue.A, ScaleValue.B, ScaleValue.C, ScaleValue.D, ScaleValue.E]
  );
})

test('fromJSON', () => {
  expect(KeySignature.fromJSON(JSON.parse('{"accidentals":[0,0,0,0,0,0,0],"ionianTonic":0}'))).toStrictEqual(KeySignatures.NO_ACCIDENTAL);
})