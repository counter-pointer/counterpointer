package edu.brown.cs.counterpointer.notenotation.pitch;

import edu.brown.cs.counterpointer.notenotation.exceptions.AccidentalOutOfBoundsException;
import edu.brown.cs.counterpointer.notenotation.interval.Interval;
import edu.brown.cs.counterpointer.notenotation.exceptions.TextNotFoundException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static edu.brown.cs.counterpointer.notenotation.pitch.Accidental.*;
import static edu.brown.cs.counterpointer.notenotation.pitch.ScaleValue.*;
import static org.junit.Assert.*;

public class PitchTest {
  private final Pitch c0 = new Pitch(C, 0, NATURAL);
  private final Pitch d2 = new Pitch(D, 2, NATURAL);
  private final Pitch eb0 = new Pitch(E, 0, FLAT);
  private final Pitch fbb2 = new Pitch(F, 2, DOUBLE_FLAT);
  private final Pitch gx10 = new Pitch(G, 10, DOUBLE_SHARP);
  private final Pitch aSharp5 = new Pitch(A, 5, SHARP);
  private final Pitch b4 = new Pitch(B, 4, NATURAL);

  @Test
  public void getSharpTests() throws AccidentalOutOfBoundsException {
    assertEquals(new Pitch(C, 0, SHARP), c0.sharp());
    assertEquals(new Pitch(A, 5, DOUBLE_SHARP), aSharp5.sharp());
    assertThrows(AccidentalOutOfBoundsException.class, () -> gx10.sharp());
    assertEquals(new Pitch(F, 2, FLAT), fbb2.sharp());
    assertEquals(new Pitch(E, 0, NATURAL), eb0.sharp());
  }

  @Test
  public void getFlatTests() throws AccidentalOutOfBoundsException {
    assertEquals(new Pitch(C, 0, FLAT), c0.flat());
    assertEquals(new Pitch(A, 5, NATURAL), aSharp5.flat());
    assertThrows(AccidentalOutOfBoundsException.class, () -> fbb2.flat());
    assertEquals(new Pitch(G, 10, SHARP), gx10.flat());
    assertEquals(new Pitch(E, 0, DOUBLE_FLAT), eb0.flat());
  }

  @Test
  public void areEnharmonicEquivs() {
    // E# and F are equal
    assertTrue((new Pitch(E, 0, SHARP))
        .isEnharmonic(new Pitch(F, 0, NATURAL)));
    // Cb and B are equal (but different octaves)
    assertTrue((new Pitch(C, 1, FLAT))
        .isEnharmonic(new Pitch(B, 0, NATURAL)));
    // Should be equivalent to self
    assertTrue(b4.isEnharmonic(b4));
    // Misc. tests
    assertTrue(fbb2.isEnharmonic(new Pitch(E, 2, FLAT)));
    assertTrue((new Pitch(C, 1, SHARP))
        .isEnharmonic(new Pitch(B, 0, DOUBLE_SHARP)));
    assertTrue(eb0.isEnharmonic(new Pitch(D, 0, SHARP)));
    assertTrue((new Pitch(G, 10, DOUBLE_SHARP)
        .isEnharmonic(new Pitch(B, 10, DOUBLE_FLAT))));
  }

  @Test
  public void notEnharmonicEquivs() {
    assertFalse((new Pitch(E, 0, SHARP))
        .isEnharmonic(new Pitch(D, 0, FLAT)));
    assertFalse((new Pitch(B, 0, FLAT))
        .isEnharmonic(new Pitch(B, 0, SHARP)));
    assertFalse((new Pitch(E, 0, FLAT))
        .isEnharmonic(new Pitch(D, 0, FLAT)));
    assertFalse((new Pitch(E, 0, FLAT))
        .isEnharmonic(new Pitch(E, 1, FLAT)));
    assertFalse((new Pitch(C, 0, NATURAL))
        .isEnharmonic(new Pitch(B, 0, SHARP)));
  }

  @Test
  public void intervalTests() {
    // Behavior on enharmonic equivalents
    assertEquals(new Interval(2, 3), c0.interval(eb0));
    assertEquals(new Interval(1, 3), c0.interval(new Pitch(D, 0, SHARP)));
    // Intervals that don't have names
    assertEquals(new Interval(2, 1), d2.interval(fbb2));
    //Intervals greater than an octave
    assertEquals(new Interval(40, 70), c0.interval(aSharp5));
  }

  @Test
  public void equalsTests() {
    assertNotEquals(eb0, d2);
    assertEquals(new Pitch(E, 0, FLAT), eb0);
    // Enharmonic equivalents are not equals
    assertNotEquals(new Pitch(D, 0, SHARP), eb0);
  }

  @Test
  public void compareToTests() {
    assertEquals(1, eb0.compareTo(c0));
    assertEquals(1, new Pitch(C, 1, NATURAL).compareTo(c0));
    assertEquals(eb0.compareTo(fbb2), -1);
    assertEquals(0, eb0.compareTo(new Pitch(E, 0, FLAT)));
  }

  @Test
  public void uniqueHashCodes() {
    // tests if the hashcodes generated are unique
    List<Integer> hashCodes = new ArrayList<>();
    List<Pitch> pitches = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      int accidental = (int) (Math.random() * 5);
      int scaleValue = (int) (Math.random() * 7);
      int octave = (int) (Math.random() * 4);
      Pitch newPitch = new Pitch(ScaleValue.values()[scaleValue],
          octave, Accidental.values()[accidental]);
      if (!pitches.contains(newPitch)) {
        pitches.add(newPitch);
        assertFalse(hashCodes.contains(newPitch.hashCode()));
        hashCodes.add(newPitch.hashCode());
      }
    }
  }

  @Test
  public void toStringTests() {
    assertEquals("C0", c0.toString());
    assertEquals("A#5", aSharp5.toString());
    assertThrows(TextNotFoundException.class, () -> ScaleValue.byString("asdf"));
  }
}
