package edu.brown.cs.counterpointer;

import edu.brown.cs.counterpointer.notenotation.Duration;
import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.key.Key;
import edu.brown.cs.counterpointer.notenotation.key.Mode;
import edu.brown.cs.counterpointer.notenotation.pitch.Pitch;
import edu.brown.cs.counterpointer.notenotation.pitch.ScaleValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static edu.brown.cs.counterpointer.CheckerTestingExamples.g4Whole;
import static edu.brown.cs.counterpointer.notenotation.key.KeySignatures.*;
import static edu.brown.cs.counterpointer.notenotation.pitch.Accidental.FLAT;
import static edu.brown.cs.counterpointer.notenotation.pitch.Accidental.NATURAL;
import static edu.brown.cs.counterpointer.notenotation.pitch.Accidental.SHARP;

public class TestingExamples {
  // Notes
  public static final Note bNeg1Quarter = new Note(new Pitch(ScaleValue.B, -1, NATURAL), Duration.QUARTER);
  public static final Note c0Quarter = new Note(new Pitch(ScaleValue.C, 0, NATURAL), Duration.QUARTER);
  public static final Note cSharp0Quarter = new Note(new Pitch(ScaleValue.C, 0, SHARP), Duration.QUARTER);
  public static final Note c1Quarter = new Note(new Pitch(ScaleValue.C, 1, NATURAL), Duration.QUARTER);
  public static final Note d1Quarter = new Note(new Pitch(ScaleValue.D, 1, NATURAL), Duration.QUARTER);
  public static final Note e1Quarter = new Note(new Pitch(ScaleValue.E, 1, NATURAL), Duration.QUARTER);
  public static final Note f1Quarter = new Note(new Pitch(ScaleValue.F, 1, NATURAL), Duration.QUARTER);
  public static final Note g1Quarter = new Note(new Pitch(ScaleValue.G, 1, NATURAL), Duration.QUARTER);
  public static final Note a1Quarter = new Note(new Pitch(ScaleValue.A, 1, NATURAL), Duration.QUARTER);
  public static final Note d0Quarter = new Note(new Pitch(ScaleValue.D, 0, NATURAL), Duration.QUARTER);
  public static final Note dSharp0Quarter = new Note(new Pitch(ScaleValue.D, 0, SHARP), Duration.QUARTER);
  public static final Note eFlat0Quarter = new Note(new Pitch(ScaleValue.E, 0, FLAT), Duration.QUARTER);
  public static final Note e0Quarter = new Note(new Pitch(ScaleValue.E, 0, NATURAL), Duration.QUARTER);
  public static final Note d0Whole = new Note(new Pitch(ScaleValue.D, 0, NATURAL), Duration.WHOLE);
  public static final Note f0Quarter = new Note(new Pitch(ScaleValue.F, 0, NATURAL), Duration.QUARTER);
  public static final Note g0Quarter = new Note(new Pitch(ScaleValue.G, 0, NATURAL), Duration.QUARTER);
  public static final Note a0Quarter = new Note(new Pitch(ScaleValue.A, 0, NATURAL), Duration.QUARTER);
  public static final Note fSharp0Half = new Note(new Pitch(ScaleValue.F, 0, SHARP), Duration.HALF);
  public static final Note fSharp0Quarter = new Note(new Pitch(ScaleValue.F, 0, SHARP), Duration.QUARTER);
  public static final Note gSharp0Half = new Note(new Pitch(ScaleValue.G, 0, SHARP), Duration.HALF);
  public static final Note aFlat0Half = new Note(new Pitch(ScaleValue.A, 0, FLAT), Duration.HALF);
  public static final Note c0Whole = new Note(new Pitch(ScaleValue.C, 0, NATURAL), Duration.WHOLE);
  public static final Note b0Quarter = new Note(new Pitch(ScaleValue.B, 0, NATURAL), Duration.QUARTER);
  public static final Note halfRest = new Note(null, Duration.HALF);
  public static final Note wholeRest = new Note(null, Duration.WHOLE);
  public static final Note quarterRest = new Note(null, Duration.QUARTER);
  public static final Note g0Half = new Note(new Pitch(ScaleValue.G, 0, NATURAL), Duration.HALF);
  public static final Note f0Half = new Note(new Pitch(ScaleValue.F, 0, NATURAL), Duration.HALF);
  public static final Note a0Half = new Note(new Pitch(ScaleValue.A, 0, NATURAL), Duration.HALF);
  public static final Note c2Quarter = new Note(new Pitch(ScaleValue.C, 2, NATURAL), Duration.QUARTER);
  public static final Note d2Quarter = new Note(new Pitch(ScaleValue.D, 2, NATURAL), Duration.QUARTER);

  // Melodies
  public static final Melody jumpy = new Melody(Arrays.asList(c0Quarter, c1Quarter, c0Quarter, c1Quarter),
      new Key(NO_ACCIDENTAL, Mode.DORIAN),
      Duration.QUARTER);
  public static final Melody twoQuarterNote = new Melody(Arrays.asList(c0Quarter, d0Quarter),
      new Key(NO_ACCIDENTAL, Mode.DORIAN),
      Duration.QUARTER);
  public static final Melody conjunctVascillateOffset =
      new Melody(Arrays.asList(quarterRest, quarterRest, f0Quarter, g0Quarter),
      new Key(NO_ACCIDENTAL, Mode.DORIAN),
      Duration.QUARTER);
  // The following two melodies will make parallel tritones
  public static final Melody conjunctVascillate =
      new Melody(Arrays.asList(c0Quarter, d0Quarter, c0Quarter, d0Quarter),
      new Key(NO_ACCIDENTAL, Mode.DORIAN),
      Duration.QUARTER);
  public static final Melody twoHalfNote = new Melody(Arrays.asList(gSharp0Half, fSharp0Half),
      new Key(NO_ACCIDENTAL, Mode.DORIAN),
      Duration.QUARTER);
  public static final Melody oneHalfNote = new Melody(Collections.singletonList(gSharp0Half),
      new Key(NO_ACCIDENTAL, Mode.DORIAN),
      Duration.QUARTER);
  public static final Melody empty = new Melody(new ArrayList<>(),
      new Key(NO_ACCIDENTAL, Mode.DORIAN),
      Duration.QUARTER);
  public static final Melody oneWholeNote = new Melody(
      Collections.singletonList(c0Whole),
      new Key(NO_ACCIDENTAL, Mode.DORIAN),
      Duration.QUARTER);
  public static final Melody twoWholeNotes = new Melody(
      Arrays.asList(c0Whole, c0Whole),
      new Key(NO_ACCIDENTAL, Mode.DORIAN),
      Duration.QUARTER);
  public static final Melody halfRests = new Melody(Arrays.asList(halfRest, halfRest, halfRest, halfRest),
      new Key(FIVE_FLAT, Mode.MIXOLYDIAN),
      Duration.EIGHTH);
  public static final Melody phrygianMelody = new Melody(Arrays.asList(f0Quarter, f0Quarter),
      new Key(FIVE_FLAT, Mode.PHRYGIAN),
      Duration.EIGHTH);
  public static final Melody dissonanceOutline = new Melody(Arrays.asList(c0Quarter, fSharp0Half),
      new Key(NO_ACCIDENTAL, Mode.DORIAN),
      Duration.QUARTER);
  public static final Melody longMelody =
      new Melody(Arrays.asList(c0Quarter, fSharp0Half, f0Quarter, g0Quarter, gSharp0Half),
      new Key(NO_ACCIDENTAL, Mode.DORIAN),
      Duration.QUARTER);
  public static final Melody longMelody2 =
      new Melody(Arrays.asList(c0Quarter, d0Quarter, f0Quarter, g0Quarter, a0Quarter),
          new Key(NO_ACCIDENTAL, Mode.IONIAN),
          Duration.QUARTER);
  public static final Melody longMelody3 =
    new Melody(Arrays.asList(a0Quarter, f0Quarter, d1Quarter, e1Quarter, e1Quarter, d1Quarter),
      new Key(NO_ACCIDENTAL, Mode.IONIAN),
      Duration.QUARTER);
  public static final Melody fourQuarterMelody =
      new Melody(Arrays.asList(a0Quarter, b0Quarter, d1Quarter, e1Quarter),
          new Key(NO_ACCIDENTAL, Mode.IONIAN),
          Duration.QUARTER);
  public static final Melody fourQuarterMelody2 =
    new Melody(Arrays.asList(g0Quarter, a0Quarter, a0Quarter, e1Quarter),
      new Key(NO_ACCIDENTAL, Mode.IONIAN),
      Duration.QUARTER);
  public static final Melody twoQuarterMelody =
      new Melody(Arrays.asList(a0Quarter, b0Quarter),
          new Key(NO_ACCIDENTAL, Mode.IONIAN),
          Duration.QUARTER);
  public static final Melody philipGlass =
      new Melody(Arrays.asList(a0Quarter, a0Quarter, a0Quarter, a0Quarter, a0Quarter, a0Quarter, a0Quarter),
          new Key(FOUR_SHARP, Mode.PHRYGIAN),
          Duration.QUARTER);
  public static final Melody threeNoteMelody =
      new Melody(Arrays.asList(c0Quarter, d0Quarter, f0Quarter),
          new Key(FOUR_SHARP, Mode.PHRYGIAN),
          Duration.QUARTER);
  public static final Melody mixolydianMelody =
      new Melody(Arrays.asList(c0Quarter, d0Quarter),
          new Key(NO_ACCIDENTAL, Mode.MIXOLYDIAN),
          Duration.QUARTER);
  public static final Melody sevenNoteStatic =
      new Melody(Arrays.asList(c0Quarter, d0Quarter, c0Quarter, eFlat0Quarter, c0Quarter, d0Quarter, d0Quarter),
          new Key(NO_ACCIDENTAL, Mode.MIXOLYDIAN),
          Duration.QUARTER);
  public static final Melody sevenNoteNotStatic =
      new Melody(Arrays.asList(c0Quarter, d0Quarter, b0Quarter, c0Quarter, a0Quarter, g0Quarter, f0Quarter),
          new Key(NO_ACCIDENTAL, Mode.MIXOLYDIAN),
          Duration.QUARTER);
  public static final Melody fifteenSecondsPerNote =
      new Melody(Arrays.asList(c0Whole, c0Whole, c0Whole, c0Whole, c0Whole, c0Whole, c0Whole),
          new Key(FOUR_SHARP, Mode.PHRYGIAN),
          Duration.QUARTER);
  public static final Melody fifthLeap =
      new Melody(Arrays.asList(e1Quarter, a0Quarter),
          new Key(NO_ACCIDENTAL, Mode.PHRYGIAN),
          Duration.QUARTER);
  public static final Melody fourthLeap =
      new Melody(Arrays.asList(a0Quarter, d1Quarter),
          new Key(NO_ACCIDENTAL, Mode.PHRYGIAN),
          Duration.QUARTER);
  public static final Melody aMelody =
      new Melody(Arrays.asList(a0Quarter),
          new Key(NO_ACCIDENTAL, Mode.PHRYGIAN),
          Duration.QUARTER);
  public static final Melody ascFifth =
      new Melody(Arrays.asList(a0Quarter, e0Quarter),
          new Key(NO_ACCIDENTAL, Mode.PHRYGIAN),
          Duration.QUARTER);
  public static final Melody oneG =
      new Melody(Arrays.asList(g4Whole),
          new Key(NO_ACCIDENTAL, Mode.MIXOLYDIAN),
          Duration.QUARTER);
  public static final Melody twoNoteMelody2 =
    new Melody(Arrays.asList(g0Quarter, f0Quarter),
      new Key(NO_ACCIDENTAL, Mode.MIXOLYDIAN),
      Duration.QUARTER);
  public static final Melody twoWhole =
    new Melody(Arrays.asList(c0Whole, d0Whole),
      new Key(NO_ACCIDENTAL, Mode.MIXOLYDIAN),
      Duration.QUARTER);
  public static final Melody twoHalf =
    new Melody(Arrays.asList(g0Half, f0Half),
      new Key(NO_ACCIDENTAL, Mode.MIXOLYDIAN),
      Duration.QUARTER);
  public static final Melody quarterMelody =
    new Melody(Arrays.asList(c0Quarter, fSharp0Quarter, g0Quarter, gSharp0Half),
      new Key(NO_ACCIDENTAL, Mode.MIXOLYDIAN),
      Duration.QUARTER);
  public static final Melody quarterMelody2 =
    new Melody(Arrays.asList(a0Quarter, d1Quarter, e1Quarter),
      new Key(NO_ACCIDENTAL, Mode.MIXOLYDIAN),
      Duration.QUARTER);
  public static final Melody aeolianOneQuarter =
      new Melody(Arrays.asList(a0Quarter),
          new Key(NO_ACCIDENTAL, Mode.AEOLIAN),
          Duration.QUARTER);
}
