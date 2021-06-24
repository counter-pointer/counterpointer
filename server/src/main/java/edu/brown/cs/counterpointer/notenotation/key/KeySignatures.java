package edu.brown.cs.counterpointer.notenotation.key;

import edu.brown.cs.counterpointer.notenotation.exceptions.InvalidScaleException;
import edu.brown.cs.counterpointer.notenotation.pitch.Accidental;
import edu.brown.cs.counterpointer.notenotation.pitch.ScaleValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static edu.brown.cs.counterpointer.notenotation.pitch.Accidental.FLAT;
import static edu.brown.cs.counterpointer.notenotation.pitch.Accidental.NATURAL;
import static edu.brown.cs.counterpointer.notenotation.pitch.Accidental.SHARP;

/**
 * A utility class storing all key signatures used and accepted during the common practice period
 * of music (no mode specified).
 */
public final class KeySignatures {
  /**
   * Disable constructor.
   */
  private KeySignatures() {
    throw new AssertionError("Instantiating static class");
  }

  public static final KeySignature SEVEN_FLAT = new KeySignature(
      List.of(FLAT, FLAT, FLAT, FLAT, FLAT, FLAT, FLAT), ScaleValue.C
  );
  public static final KeySignature SIX_FLAT = new KeySignature(
      List.of(FLAT, FLAT, FLAT, FLAT, FLAT, FLAT, NATURAL), ScaleValue.G
  );
  public static final KeySignature FIVE_FLAT = new KeySignature(
      List.of(FLAT, FLAT, NATURAL, FLAT, FLAT, FLAT, NATURAL), ScaleValue.D
  );
  public static final KeySignature FOUR_FLAT = new KeySignature(
      List.of(FLAT, FLAT, NATURAL, FLAT, FLAT, NATURAL, NATURAL), ScaleValue.A
  );
  public static final KeySignature THREE_FLAT = new KeySignature(
      List.of(FLAT, NATURAL, NATURAL, FLAT, FLAT, NATURAL, NATURAL), ScaleValue.E
  );
  public static final KeySignature TWO_FLAT = new KeySignature(
      List.of(FLAT, NATURAL, NATURAL, FLAT, NATURAL, NATURAL, NATURAL), ScaleValue.B
  );
  public static final KeySignature ONE_FLAT = new KeySignature(
      List.of(NATURAL, NATURAL, NATURAL, FLAT, NATURAL, NATURAL, NATURAL), ScaleValue.F
  );
  public static final KeySignature NO_ACCIDENTAL = new KeySignature(
      List.of(NATURAL, NATURAL, NATURAL, NATURAL, NATURAL, NATURAL, NATURAL), ScaleValue.C
  );
  public static final KeySignature ONE_SHARP = new KeySignature(
      List.of(NATURAL, NATURAL, NATURAL, NATURAL, NATURAL, NATURAL, SHARP), ScaleValue.G
  );
  public static final KeySignature TWO_SHARP = new KeySignature(
      List.of(NATURAL, NATURAL, SHARP, NATURAL, NATURAL, NATURAL, SHARP), ScaleValue.D
  );
  public static final KeySignature THREE_SHARP = new KeySignature(
      List.of(NATURAL, NATURAL, SHARP, NATURAL, NATURAL, SHARP, SHARP), ScaleValue.A
  );
  public static final KeySignature FOUR_SHARP = new KeySignature(
      List.of(NATURAL, SHARP, SHARP, NATURAL, NATURAL, SHARP, SHARP), ScaleValue.E
  );
  public static final KeySignature FIVE_SHARP = new KeySignature(
      List.of(NATURAL, SHARP, SHARP, NATURAL, SHARP, SHARP, SHARP), ScaleValue.B
  );

  public static final KeySignature SIX_SHARP = new KeySignature(
      List.of(SHARP, SHARP, SHARP, NATURAL, SHARP, SHARP, SHARP), ScaleValue.F
  );
  public static final KeySignature SEVEN_SHARP = new KeySignature(
      List.of(SHARP, SHARP, SHARP, SHARP, SHARP, SHARP, SHARP), ScaleValue.C
  );

  private static final List<KeySignature> ALL_KEY_SIGNATURES = new ArrayList<>(Arrays.asList(
      SEVEN_FLAT, SIX_FLAT, FIVE_FLAT, FOUR_FLAT, THREE_FLAT, TWO_FLAT, ONE_FLAT, NO_ACCIDENTAL,
      SEVEN_SHARP, SIX_SHARP, FIVE_SHARP, FOUR_SHARP, THREE_SHARP, TWO_SHARP, ONE_SHARP
  ));

  public static KeySignature getByNoteAtDegree(
      ScaleValue scaleValue, Accidental accidental, int degree
  ) {
    for (KeySignature key : ALL_KEY_SIGNATURES) {
      List<ScaleValue> noteNameScale = key.getScaleLetterNames(key.getIonianTonic());
      List<Accidental> keyAccidentals = key.getAccidentals();

      if (noteNameScale.get(degree) == scaleValue
          && keyAccidentals.get(degree) == accidental) {
        return key;
      }
    }
    throw new InvalidScaleException("Key signature not found");
  }
}
