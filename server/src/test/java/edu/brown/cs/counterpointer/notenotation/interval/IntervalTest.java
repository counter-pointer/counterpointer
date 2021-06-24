package edu.brown.cs.counterpointer.notenotation.interval;

import edu.brown.cs.counterpointer.notenotation.exceptions.AccidentalOutOfBoundsException;
import edu.brown.cs.counterpointer.notenotation.exceptions.NegativeDistanceException;
import org.junit.Test;

import static edu.brown.cs.counterpointer.notenotation.interval.IntervalQuality.*;
import static org.junit.Assert.*;

public class IntervalTest {
  private final Interval per12 = new Interval(11, 19);
  private final Interval per5 = new Interval(4, 7);
  private final Interval per1 = new Interval(0, 0);
  private final Interval min3 = new Interval(2, 3);
  private final Interval maj6 = new Interval(5, 9);
  private final Interval aug2 = new Interval(1, 3);
  private final Interval dim5 = new Interval(4, 6);
  private final Interval min7 = new Interval(6, 10);
  private final Interval wacky5 = new Interval(4, 78);
  private final Interval dim8 = new Interval(7, 11);
  private final Interval dim16 = new Interval(14, 23);

  @Test
  public void baseIntervalTests() {
    assertEquals(per5, per12.getBaseInterval());
    assertNotEquals(dim5, per5.getBaseInterval());
    assertEquals(maj6, maj6.getBaseInterval());
    assertNotEquals(min3, aug2.getBaseInterval());
    assertEquals((new Interval(7, 12)).getBaseInterval(), per1);
    assertNotEquals(min3, min7.getBaseInterval());
    assertEquals(dim8, dim8.getBaseInterval());
    assertEquals(dim8, dim16.getBaseInterval());
  }

  @Test
  public void getIntervalQualityTests() throws AccidentalOutOfBoundsException {
    //Perfect intervals
    assertEquals(PERFECT, per1.getIntervalQuality());
    assertEquals(DIMINISHED, dim5.getIntervalQuality());
    assertEquals(AUGMENTED, (new Interval(3, 6)).getIntervalQuality());
    // Non-perfect intervals
    assertEquals(MAJOR, maj6.getIntervalQuality());
    assertEquals(MINOR, min3.getIntervalQuality());
    assertEquals(AUGMENTED, aug2.getIntervalQuality());
    assertEquals(DIMINISHED, (new Interval(1, 0)).getIntervalQuality());
    assertEquals(MINOR, min7.getIntervalQuality());
    assertEquals(DIMINISHED, dim16.getIntervalQuality());
    // Broken intervals
    assertThrows(NegativeDistanceException.class, () -> new Interval(1, -1));
    assertEquals(UNCLASSIFIED, (new Interval(8, 20)).getIntervalQuality());
    assertEquals(UNCLASSIFIED, wacky5.getIntervalQuality());
  }

  @Test
  public void toStringTests() {
    assertEquals("Major 6", maj6.toString());
    assertEquals("Invalid interval", wacky5.toString());
  }
}
