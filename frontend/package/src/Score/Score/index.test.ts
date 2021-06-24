
import Score from "."
import Clef from "../Clef"
import Duration from "../Duration"
import Key from "../Key"
import KeySignature from "../Key/KeySignature"
import KeySignatures from "../Key/KeySignatures"
import Mode from "../Key/Mode"
import Melody from "../Melody"
import Note from "../Note"
import Pitch from "../Pitch"
import Accidental from "../Pitch/Accidental"
import ScaleValue from "../Pitch/ScaleValue"
import Role from "./Role"




test("key", () => {
  let melodyHigher = new Score(new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN), true)
  melodyHigher.melody.append(new Note(new Pitch(ScaleValue.C, 2, Accidental.NAT), Duration.QUARTER));
  const melodyLower = new Score(new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN), false)
  //getting:
  expect(melodyHigher.key).toStrictEqual(new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN))
  expect(melodyLower.key).toStrictEqual(new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN))
  //setting, making sure it trails to the melodies inside as well.
  melodyHigher.setKey(new Key(KeySignatures.ONE_FLAT, Mode.MIXOLYDIAN));
  expect(melodyHigher.key).toStrictEqual(new Key(KeySignatures.ONE_FLAT, Mode.MIXOLYDIAN))
  expect(melodyHigher.melody.key).toStrictEqual(new Key(KeySignatures.ONE_FLAT, Mode.MIXOLYDIAN))
  expect(melodyHigher.counterMelody.key).toStrictEqual(new Key(KeySignatures.ONE_FLAT, Mode.MIXOLYDIAN))
})

test("melodyHigher", () => {
  let melodyHigher = new Score(new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN), true)
  const melodyLower = new Score(new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN), false)

  expect(melodyHigher.isMelodyHigher).toBe(true);
  expect(melodyLower.isMelodyHigher).toBe(false);
  }
);


test("fromJSON", () => {
  const testMelody = new Score(new Key(KeySignatures.ONE_FLAT, Mode.MIXOLYDIAN), true);
  testMelody.melody.append(new Note(new Pitch(ScaleValue.C, 2, Accidental.NAT), Duration.QUARTER));
  testMelody.counterMelody.append(new Note(new Pitch(ScaleValue.C, 2, Accidental.NAT), Duration.QUARTER)); 
  expect(Score.fromJSON(JSON.parse('{"melody":{"notes":[{"pitch":{"scaleValue":0,"octave":2,"accidental":0},"duration":1.0}],"key":{"keySignature":{"accidentals":[0,0,0,-1,0,0,0],"ionianTonic":3},"mode":4},"unitBeat":1.0},"counterMelody":{"notes":[{"pitch":{"scaleValue":0,"octave":2,"accidental":0},"duration":1.0}],"key":{"keySignature":{"accidentals":[0,0,0,-1,0,0,0],"ionianTonic":3},"mode":4},"unitBeat":1.0},"melodyIsUpper":true}'))).toStrictEqual(testMelody);
})

//TODO: add more to this test
test("melody, countermelody", () => {
  let melodyHigher = new Score(new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN), true)
  let melodyLower = new Score(new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN), false)

  expect(melodyHigher.melody).toStrictEqual(new Melody([], new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN)))
  expect(melodyLower.melody).toStrictEqual(new Melody([], new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN)))
} )

test("get/set melodyByRole", () => {
  const CMaj = new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN) 
  let melodyHigher = new Score(CMaj, true)
  let melodyLower = new Score(CMaj, false)
  const C2 = new Note(new Pitch(ScaleValue.C, 2, Accidental.NAT), Duration.QUARTER)
  melodyHigher.melody.append(C2);
  melodyLower.melody.append(C2);
  
  expect(melodyHigher.getMelodyByRole(Role.MELODY).length()).toStrictEqual(1)
  expect(melodyLower.getMelodyByRole(Role.MELODY).length()).toStrictEqual(1)
  expect(melodyHigher.getMelodyByRole(Role.COUNTERMELODY).length()).toStrictEqual(0)

  expect(melodyHigher.getMelodyByRole(Role.MELODY).getNote(0)).toStrictEqual(C2)

  melodyHigher.setMelodyByRole(Role.MELODY,new Melody([],CMaj))
  expect(melodyHigher.getMelodyByRole(Role.MELODY).length()).toStrictEqual(0)
})


test("getClefByRole", () => {
  const CMaj = new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN) 
  let melodyHigher = new Score(CMaj, true)
  let melodyLower = new Score(CMaj, false)

  expect(melodyHigher.getClefByRole(Role.MELODY)).toStrictEqual(Clef.ALTO)
  expect(melodyLower.getClefByRole(Role.MELODY)).toStrictEqual(Clef.ALTO)

  expect(melodyHigher.getClefByRole(Role.COUNTERMELODY)).toStrictEqual(Clef.BASS)
  expect(melodyLower.getClefByRole(Role.COUNTERMELODY)).toStrictEqual(Clef.TREBLE)
})

test("getABCMapping", () => {
  const C2 = new Note(new Pitch(ScaleValue.C, 2, Accidental.NAT), Duration.QUARTER)
  const Eshsh8 = new Note(new Pitch(ScaleValue.E, 8, Accidental.DBLSHARP), Duration.WHOLE)

  let melodyHigher = new Score(new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN), true)
  melodyHigher.getMelodyByRole(Role.MELODY).append(C2);
  expect(melodyHigher.getABCMapping()).toStrictEqual([
    "K:Cmaj\nV:M clef=alto\nV:CM clef=bass\n[V:M]C,,2|\n[V:CM]|",
    new Map([[41, [Role.MELODY, 0]]]),
    new Map([[Role.MELODY, new Map([[0, 41]])],[Role.COUNTERMELODY, new Map()]])
  ]);
  melodyHigher.getMelodyByRole(Role.COUNTERMELODY).append(C2);
  expect(melodyHigher.getABCMapping()).toStrictEqual([
    "K:Cmaj\nV:M clef=alto\nV:CM clef=bass\n[V:M]C,,2|\n[V:CM]C,,2|",
    new Map([[41, [Role.MELODY, 0]], [53, [Role.COUNTERMELODY, 0]]]),
    new Map([[Role.MELODY, new Map([[0, 41]])],[Role.COUNTERMELODY, new Map([[0,53]])]])
  ]);
  melodyHigher.getMelodyByRole(Role.MELODY).append(Eshsh8);
  expect(melodyHigher.getABCMapping()).toStrictEqual([
    "K:Cmaj\nV:M clef=alto\nV:CM clef=bass\n[V:M]C,,2^^E''''6-|^^E''''2|\n[V:CM]C,,2|",
    new Map([[41, [Role.MELODY, 0]], [45, [Role.MELODY, 1]], [55, [Role.MELODY, 1]], [71, [Role.COUNTERMELODY, 0]]]),
    new Map([[Role.MELODY, new Map([[0, 41],[1,55]])],[Role.COUNTERMELODY, new Map([[0,71]])]])
  ]);
  melodyHigher.getMelodyByRole(Role.COUNTERMELODY).append(C2)
  melodyHigher.getMelodyByRole(Role.COUNTERMELODY).append(C2)
  melodyHigher.getMelodyByRole(Role.COUNTERMELODY).append(C2)
  melodyHigher.getMelodyByRole(Role.COUNTERMELODY).append(Eshsh8)
  expect(melodyHigher.getABCMapping()).toStrictEqual([
    "K:Cmaj\nV:M clef=alto\nV:CM clef=bass\n[V:M]C,,2^^E''''6-|^^E''''2|\n[V:CM]C,,2C,,2C,,2C,,2|^^E''''8||",
    new Map([[41, [Role.MELODY, 0]], [45, [Role.MELODY, 1]], [55, [Role.MELODY, 1]], [71, [Role.COUNTERMELODY, 0]],
      [75, [Role.COUNTERMELODY, 1]], [79, [Role.COUNTERMELODY, 2]], [83, [Role.COUNTERMELODY, 3]], [88, [Role.COUNTERMELODY, 4]]]),
    new Map([[Role.MELODY, new Map([[0, 41],[1,55]])],[Role.COUNTERMELODY, new Map([[0,71],[1,75],[2,79],[3,83],[4,88]])]])
  ]);
})