package edu.brown.cs.counterpointer;

import edu.brown.cs.counterpointer.analysis.Score;
import edu.brown.cs.counterpointer.notenotation.Duration;
import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.key.Key;
import edu.brown.cs.counterpointer.notenotation.key.KeySignatures;
import edu.brown.cs.counterpointer.notenotation.key.Mode;
import edu.brown.cs.counterpointer.notenotation.pitch.Accidental;
import edu.brown.cs.counterpointer.notenotation.pitch.Pitch;
import edu.brown.cs.counterpointer.notenotation.pitch.ScaleValue;

import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import java.util.Arrays;

import static edu.brown.cs.counterpointer.notenotation.key.KeySignatures.THREE_FLAT;

public class CheckerTestingExamples {
  // Notes
  // WHOLE NOTES
  // Octave 2
  public static final Note e2Whole = new Note(new Pitch(ScaleValue.E, 2, Accidental.NATURAL), Duration.WHOLE);
  public static final Note fSharp2Whole = new Note(new Pitch(ScaleValue.F, 2, Accidental.SHARP), Duration.WHOLE);
  public static final Note g2Whole = new Note(new Pitch(ScaleValue.G, 2, Accidental.NATURAL), Duration.WHOLE);
  public static final Note a2Whole = new Note(new Pitch(ScaleValue.A, 2, Accidental.NATURAL), Duration.WHOLE);
  public static final Note bFlat2Whole = new Note(new Pitch(ScaleValue.B, 2, Accidental.FLAT), Duration.WHOLE);
  public static final Note b2Whole = new Note(new Pitch(ScaleValue.B, 2, Accidental.NATURAL), Duration.WHOLE);
  // Octave 3
  public static final Note c3Whole = new Note(new Pitch(ScaleValue.C, 3, Accidental.NATURAL), Duration.WHOLE);
  public static final Note d3Whole = new Note(new Pitch(ScaleValue.D, 3, Accidental.NATURAL), Duration.WHOLE);
  public static final Note eFlat3Whole = new Note(new Pitch(ScaleValue.E, 3, Accidental.FLAT), Duration.WHOLE);
  public static final Note e3Whole = new Note(new Pitch(ScaleValue.E, 3, Accidental.NATURAL), Duration.WHOLE);
  public static final Note f3Whole = new Note(new Pitch(ScaleValue.F, 3, Accidental.NATURAL), Duration.WHOLE);
  public static final Note fSharp3Whole = new Note(new Pitch(ScaleValue.F, 3, Accidental.SHARP), Duration.WHOLE);
  public static final Note g3Whole = new Note(new Pitch(ScaleValue.G, 3, Accidental.NATURAL), Duration.WHOLE);
  public static final Note gSharp3Whole = new Note(new Pitch(ScaleValue.G, 3, Accidental.SHARP), Duration.WHOLE);
  public static final Note a3Whole = new Note(new Pitch(ScaleValue.A, 3, Accidental.NATURAL), Duration.WHOLE);
  public static final Note bFlat3Whole = new Note(new Pitch(ScaleValue.B, 3, Accidental.FLAT), Duration.WHOLE);
  public static final Note b3Whole = new Note(new Pitch(ScaleValue.B, 3, Accidental.NATURAL), Duration.WHOLE);
  // Octave 4
  public static final Note c4Whole = new Note(new Pitch(ScaleValue.C, 4, Accidental.NATURAL), Duration.WHOLE);
  public static final Note dFlat4Whole = new Note(new Pitch(ScaleValue.D, 4, Accidental.FLAT), Duration.WHOLE);
  public static final Note d4Whole = new Note(new Pitch(ScaleValue.D, 4, Accidental.NATURAL), Duration.WHOLE);
  public static final Note eFlat4Whole = new Note(new Pitch(ScaleValue.E, 4, Accidental.FLAT), Duration.WHOLE);
  public static final Note e4Whole = new Note(new Pitch(ScaleValue.E, 4, Accidental.NATURAL), Duration.WHOLE);
  public static final Note f4Whole = new Note(new Pitch(ScaleValue.F, 4, Accidental.NATURAL), Duration.WHOLE);
  public static final Note fSharp4Whole = new Note(new Pitch(ScaleValue.F, 4, Accidental.SHARP), Duration.WHOLE);
  public static final Note g4Whole = new Note(new Pitch(ScaleValue.G, 4, Accidental.NATURAL), Duration.WHOLE);
  public static final Note gSharp4Whole = new Note(new Pitch(ScaleValue.G, 4, Accidental.SHARP), Duration.WHOLE);
  public static final Note a4Whole = new Note(new Pitch(ScaleValue.A, 4, Accidental.NATURAL), Duration.WHOLE);
  public static final Note b4Whole = new Note(new Pitch(ScaleValue.B, 4, Accidental.NATURAL), Duration.WHOLE);
  // Octave 5
  public static final Note c5Whole = new Note(new Pitch(ScaleValue.C, 5, Accidental.NATURAL), Duration.WHOLE);

  // HALF NOTES
  // Octave 2
  public static final Note e2Half = new Note(new Pitch(ScaleValue.E, 2, Accidental.NATURAL), Duration.HALF);
  public static final Note fSharp2Half = new Note(new Pitch(ScaleValue.F, 2, Accidental.SHARP), Duration.HALF);
  public static final Note g2Half = new Note(new Pitch(ScaleValue.G, 2, Accidental.NATURAL), Duration.HALF);
  public static final Note a2Half = new Note(new Pitch(ScaleValue.A, 2, Accidental.NATURAL), Duration.HALF);
  public static final Note bFlat2Half = new Note(new Pitch(ScaleValue.B, 2, Accidental.FLAT), Duration.HALF);
  public static final Note b2Half = new Note(new Pitch(ScaleValue.B, 2, Accidental.NATURAL), Duration.HALF);
  // Octave 3
  public static final Note c3Half = new Note(new Pitch(ScaleValue.C, 3, Accidental.NATURAL), Duration.HALF);
  public static final Note d3Half = new Note(new Pitch(ScaleValue.D, 3, Accidental.NATURAL), Duration.HALF);
  public static final Note eFlat3Half = new Note(new Pitch(ScaleValue.E, 3, Accidental.FLAT), Duration.HALF);
  public static final Note e3Half = new Note(new Pitch(ScaleValue.E, 3, Accidental.NATURAL), Duration.HALF);
  public static final Note f3Half = new Note(new Pitch(ScaleValue.F, 3, Accidental.NATURAL), Duration.HALF);
  public static final Note fSharp3Half = new Note(new Pitch(ScaleValue.F, 3, Accidental.SHARP), Duration.HALF);
  public static final Note g3Half = new Note(new Pitch(ScaleValue.G, 3, Accidental.NATURAL), Duration.HALF);
  public static final Note gSharp3Half = new Note(new Pitch(ScaleValue.G, 3, Accidental.SHARP), Duration.HALF);
  public static final Note a3Half = new Note(new Pitch(ScaleValue.A, 3, Accidental.NATURAL), Duration.HALF);
  public static final Note bFlat3Half = new Note(new Pitch(ScaleValue.B, 3, Accidental.FLAT), Duration.HALF);
  public static final Note b3Half = new Note(new Pitch(ScaleValue.B, 3, Accidental.NATURAL), Duration.HALF);

  // Melodies
  // Mixolydian melodies
  public static final Melody outline7thMelody = new Melody(
    Arrays.asList(d4Whole, f4Whole, g4Whole, g4Whole, b4Whole, c5Whole, g4Whole, fSharp4Whole, g4Whole),
    new Key(KeySignatures.NO_ACCIDENTAL, Mode.MIXOLYDIAN),
    Duration.QUARTER);
  public static final Melody cantusFirmus1 = new Melody(
    Arrays.asList(g3Whole, d4Whole, b3Whole, e4Whole, d4Whole, c4Whole, b3Whole, a3Whole, g3Whole),
    new Key(KeySignatures.NO_ACCIDENTAL, Mode.MIXOLYDIAN),
    Duration.QUARTER);
  public static final Melody lowerMelody1 = new Melody(
    Arrays.asList(g3Whole, f3Whole, g3Whole, e3Whole, f3Whole, a3Whole, g3Whole, fSharp3Whole, g3Whole),
    new Key(KeySignatures.NO_ACCIDENTAL, Mode.MIXOLYDIAN),
    Duration.QUARTER);
  // Aeolian melodies
  public static final Melody aeolianUpperCorrect = new Melody(
    Arrays.asList(a4Whole, g4Whole, b4Whole, a4Whole, g4Whole, f4Whole, f4Whole, e4Whole, gSharp4Whole, a4Whole),
    new Key(KeySignatures.NO_ACCIDENTAL, Mode.AEOLIAN),
    Duration.QUARTER);
  public static final Melody aeolianLowerCorrect = new Melody(
    Arrays.asList(a2Whole, g2Whole, b2Whole, c3Whole, g3Whole, f3Whole, f3Whole, e3Whole, gSharp3Whole, a3Whole),
    new Key(KeySignatures.NO_ACCIDENTAL, Mode.AEOLIAN),
    Duration.QUARTER);
  public static final Melody aeolianLowerIncorrect = new Melody(
    Arrays.asList(a2Whole, g2Whole, b2Whole, c3Whole, d3Whole, f3Whole, f3Whole, e3Whole, gSharp3Whole, a3Whole),
    new Key(KeySignatures.NO_ACCIDENTAL, Mode.AEOLIAN),
    Duration.QUARTER);
  public static final Melody cantusFirmus2 = new Melody(
    Arrays.asList(a3Whole, e3Whole, g3Whole, a3Whole, b3Whole, a3Whole, d4Whole, c4Whole, b3Whole, a3Whole),
    new Key(KeySignatures.NO_ACCIDENTAL, Mode.AEOLIAN),
    Duration.QUARTER);
  // g aeolian
  public static final Melody gAeolianCantus = new Melody(
      Arrays.asList(g3Whole, g3Whole, f3Whole, bFlat3Whole, a3Whole, g3Whole, a3Whole, g3Whole),
      new Key(KeySignatures.TWO_FLAT, Mode.AEOLIAN),
      Duration.QUARTER);
  public static final Melody gAeolianLowerCm = new Melody(
      Arrays.asList(g2Whole, c3Whole, d3Whole, bFlat2Whole, c3Whole, e2Whole, fSharp2Whole, g2Whole),
      new Key(KeySignatures.TWO_FLAT, Mode.AEOLIAN),
      Duration.QUARTER);
  // C Phrygian
  public static final Melody cPhrygianCantus = new Melody(
      Arrays.asList(c4Whole, bFlat3Whole, dFlat4Whole, eFlat4Whole, dFlat4Whole, c4Whole),
      new Key(KeySignatures.FOUR_FLAT, Mode.PHRYGIAN),
      Duration.QUARTER);
  public static final Melody cPhrygianCountermelodyCorrect = new Melody(
      Arrays.asList(c3Whole, g3Whole, f3Whole, eFlat3Whole, bFlat2Whole, c3Whole),
      new Key(KeySignatures.FOUR_FLAT, Mode.PHRYGIAN),
      Duration.QUARTER);
  public static final Melody cPhrygianCountermelodyIncorrectLT = new Melody(
      Arrays.asList(c3Whole, g3Whole, f3Whole, eFlat3Whole, b2Whole, c3Whole),
      new Key(KeySignatures.FOUR_FLAT, Mode.PHRYGIAN),
      Duration.QUARTER);

  // G mixolydian
  public static final Melody gMixolydianCantus2 = new Melody(
      Arrays.asList(g3Whole, d4Whole, b3Whole, e4Whole, c4Whole, c4Whole, c4Whole, c4Whole, c4Whole, c4Whole, c4Whole,
          c4Whole, c4Whole, d4Whole, c4Whole, b3Whole, a3Whole, g3Whole),
      new Key(KeySignatures.NO_ACCIDENTAL, Mode.MIXOLYDIAN),
      Duration.QUARTER);
  public static final Melody gMixolydianCmIncorrect = new Melody(
      Arrays.asList(g3Whole, g3Whole, b2Whole, g3Whole, f3Whole, a3Whole, g2Whole, fSharp2Whole, g2Whole, a2Whole,
          e3Whole, a3Whole, e3Whole, a3Whole, b3Whole, e3Whole, e3Whole, fSharp3Whole, g3Whole),
      new Key(KeySignatures.NO_ACCIDENTAL, Mode.MIXOLYDIAN),
      Duration.QUARTER);

  // C ionian
  public static final Melody cIonianCantus = new Melody(
      Arrays.asList(c4Whole, d4Whole, e4Whole, f4Whole, e4Whole, d4Whole, c4Whole),
      new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN),
      Duration.QUARTER);
  public static final Melody cIonianIncorrectCountermelody = new Melody(
      Arrays.asList(),
      new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN),
      Duration.QUARTER);
  public static final Melody cIonianIncorrectCountermelodyWRest = new Melody(
      Arrays.asList(c4Whole, d4Whole, e4Whole, f4Whole, e4Whole, d4Whole, c4Whole),
      new Key(KeySignatures.NO_ACCIDENTAL, Mode.IONIAN),
      Duration.QUARTER);

  // C aeolian
  public static final Melody cAeolianMelody =
      new Melody(Arrays.asList(c4Whole, d4Whole, eFlat4Whole, f4Whole, g4Whole, f4Whole, eFlat4Whole, d4Whole, c4Whole),
          new Key(THREE_FLAT, Mode.AEOLIAN),
          Duration.QUARTER);
  public static final Melody cAeolianCm =
      new Melody(Arrays.asList(c3Whole),
          new Key(THREE_FLAT, Mode.AEOLIAN),
          Duration.QUARTER);

  // Score
  public static final Score outline7thScore = new Score(cantusFirmus1, outline7thMelody, false);
  public static final Score correctMixolydianScore = new Score(cantusFirmus1, lowerMelody1, true);

  public static final Score correctAeolianUpperScore = new Score(cantusFirmus2, aeolianUpperCorrect, false);
  public static final Score correctAeolianLowerScore = new Score(cantusFirmus2, aeolianLowerCorrect, true);
  public static final Score incorrectAeolianLowerScore = new Score(cantusFirmus2, aeolianLowerIncorrect, true);

  public static final Score incorrectGAeolianScore = new Score(gAeolianCantus, gAeolianLowerCm, true);

  public static final Score correctPhrygianScore = new Score(cPhrygianCantus, cPhrygianCountermelodyCorrect, true);
  public static final Score incorrectPhrygianScore = new Score(cPhrygianCantus, cPhrygianCountermelodyIncorrectLT, true);

  public static final Score incorrectMixolydianScore = new Score(gMixolydianCantus2, gMixolydianCmIncorrect, true);

  public static final Score incompleteAeolianScore = new Score(cAeolianMelody, cAeolianCm, true);
}
