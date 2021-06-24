package edu.brown.cs.counterpointer.notenotation;

import edu.brown.cs.counterpointer.notenotation.interval.Interval;
import edu.brown.cs.counterpointer.notenotation.pitch.Pitch;
import org.junit.Test;

import static edu.brown.cs.counterpointer.notenotation.Duration.*;
import static edu.brown.cs.counterpointer.notenotation.pitch.Accidental.*;
import static edu.brown.cs.counterpointer.notenotation.pitch.ScaleValue.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NoteTest {
  Note f = new Note(new Pitch(F, 0, NATURAL), WHOLE);
  Note b = new Note(new Pitch(B, 0, NATURAL), WHOLE);
  Note c0 = new Note(new Pitch(C, 0, NATURAL), WHOLE);
  Note c1 = new Note(new Pitch(C, 1, NATURAL), HALF);
  Note cSharp = new Note(new Pitch(C, 0, SHARP), WHOLE);
  Note ab = new Note(new Pitch(A, 0, FLAT), WHOLE);
  Note quarterRest = new Note(null, QUARTER);
  @Test
  public void IntervalTest() {
    assertTrue(f.interval(b).equals(new Interval(3, 6)));
    // Octaves
    assertTrue(c0.interval(c1).equals(new Interval(7, 12)));
    // Lower note to higher note
    assertTrue(c1.interval(c0).equals(new Interval(7, 12)));
    // Accidentals
    assertTrue(c0.interval(f).equals(new Interval(3, 5)));
    assertTrue(cSharp.interval(f).equals(new Interval(3, 4)));
    assertTrue(f.interval(ab).equals(new Interval(2, 3)));
  }

  @Test
  public void restTests() {
    // note, rest
    assertFalse(f.isRest());
    assertTrue(quarterRest.isRest());
    assertTrue((new Note(null, WHOLE)).isRest());
    // pitch of rests
    assertTrue(quarterRest.getPitch() == null);
    assertTrue(quarterRest.interval(f) == null);
    assertTrue(c1.interval(new Note(null, WHOLE)) == null);
  }
}
