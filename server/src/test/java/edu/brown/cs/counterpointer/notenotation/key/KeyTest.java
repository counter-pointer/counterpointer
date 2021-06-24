package edu.brown.cs.counterpointer.notenotation.key;

import edu.brown.cs.counterpointer.notenotation.Duration;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.exceptions.InvalidScaleException;
import edu.brown.cs.counterpointer.notenotation.exceptions.PitchNotInScaleException;
import edu.brown.cs.counterpointer.notenotation.exceptions.TextNotFoundException;
import edu.brown.cs.counterpointer.notenotation.pitch.Pitch;
import org.junit.Test;

import static edu.brown.cs.counterpointer.notenotation.pitch.Accidental.FLAT;
import static edu.brown.cs.counterpointer.notenotation.pitch.Accidental.NATURAL;
import static edu.brown.cs.counterpointer.notenotation.pitch.Accidental.SHARP;
import edu.brown.cs.counterpointer.notenotation.pitch.ScaleValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class KeyTest {
  private final Key aDor = new Key(KeySignatures.ONE_SHARP, Mode.DORIAN);
  private final Key cbMaj = new Key(KeySignatures.SEVEN_FLAT, Mode.IONIAN);
  private final Key cMix = new Key(KeySignatures.ONE_FLAT, Mode.MIXOLYDIAN);

  @Test
  public void stringConstructorTest() {
    assertEquals(
        aDor.getKeySignature(),
        (new Key("Ador")).getKeySignature()
    );
    assertEquals(
        new Key(KeySignatures.FIVE_SHARP, Mode.PHRYGIAN).getKeySignature(),
        (new Key("D#phr")).getKeySignature()
    );
    assertEquals(
        new Key(KeySignatures.SIX_FLAT, Mode.MIXOLYDIAN).getKeySignature(),
        (new Key("Dbmix")).getKeySignature()
    );
    assertEquals(
        new Key(KeySignatures.ONE_FLAT, Mode.AEOLIAN).getMode(),
        (new Key("Dmin")).getMode()
    );
    assertEquals(
        cMix.getKeySignature(),
        (new Key("Cmix")).getKeySignature()
    );
    assertEquals(
        cMix.getMode(),
        (new Key("Cmix")).getMode()
    );
    assertThrows(TextNotFoundException.class, () -> new Key("Dbbmin"));
    assertThrows(TextNotFoundException.class, () -> new Key("maj"));
  }

  @Test
  public void isInKeyTests() throws InvalidScaleException {
    // All-flat key
    assertTrue(cbMaj.isInKey(new Pitch(ScaleValue.C, 0, FLAT)));
    assertTrue(cbMaj.isInKey(new Pitch(ScaleValue.C, 4365, FLAT)));
    assertTrue(cbMaj.isInKey(new Pitch(ScaleValue.D, 0, FLAT)));
    // Enharmonic equivalent is not in the key
    assertFalse(cbMaj.isInKey(new Pitch(ScaleValue.E, 0, NATURAL)));
    // Key that is a mixture of sharps and naturals
    assertTrue(aDor.isInKey(new Pitch(ScaleValue.G, 0, NATURAL)));
    assertTrue(aDor.isInKey(new Pitch(ScaleValue.D, 5, NATURAL)));
    assertFalse(aDor.isInKey(new Pitch(ScaleValue.F, 58, NATURAL)));
    // isInKey() on Notes
    assertFalse(aDor.isInKey(new Note(new Pitch(ScaleValue.F, 0, NATURAL), Duration.WHOLE)));
    assertTrue(cbMaj.isInKey(new Note(new Pitch(ScaleValue.G, 0, FLAT), Duration.HALF)));
  }

  @Test
  public void isLeadingToneTests() {
    assertFalse(aDor.isLeadingTone(
        new Note(new Pitch(ScaleValue.F, 0, SHARP), Duration.QUARTER))
    );
    assertTrue(aDor.isLeadingTone(
        new Note(new Pitch(ScaleValue.G, 0, SHARP), Duration.QUARTER))
    );
    assertTrue(cbMaj.isLeadingTone(
        new Note(new Pitch(ScaleValue.B, 15, NATURAL), Duration.WHOLE))
    );
  }

  @Test
  public void ScaleDegreeTests() throws PitchNotInScaleException, InvalidScaleException {
    assertEquals(
        ScaleDegree.SUBDOMINANT,
        cbMaj.getScaleDegree(new Pitch(ScaleValue.F, 3457, FLAT))
    );
    assertThrows(PitchNotInScaleException.class,
        () -> cbMaj.getScaleDegree(new Pitch(ScaleValue.E, 2, NATURAL)));
    assertEquals(
        ScaleDegree.LEADING_TONE,
        aDor.getScaleDegree(new Pitch(ScaleValue.G, 3457, NATURAL))
    );
    assertEquals(
        ScaleDegree.TONIC,
        aDor.getScaleDegree(new Note(new Pitch(ScaleValue.A, 0, NATURAL), Duration.HALF))
    );
  }

  @Test
  public void getPitchTests() {
    assertEquals(
        new Pitch(ScaleValue.F, 8, SHARP),
        aDor.getPitch(ScaleDegree.SUBMEDIANT, 8)
    );
    assertEquals(
        new Pitch(ScaleValue.A, 8, NATURAL),
        aDor.getPitch(ScaleDegree.TONIC, 8)
    );
    assertEquals(
        new Pitch(ScaleValue.B, 0, FLAT),
        cbMaj.getPitch(ScaleDegree.LEADING_TONE, 0)
    );
  }

  @Test
  public void toStringTests() {
    assertEquals("Ador", aDor.toString());
    assertEquals("Cbmaj", cbMaj.toString());
  }
}
