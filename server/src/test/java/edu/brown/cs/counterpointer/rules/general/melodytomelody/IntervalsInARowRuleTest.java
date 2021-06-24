package edu.brown.cs.counterpointer.rules.general.melodytomelody;

import edu.brown.cs.counterpointer.TestingExamples;
import edu.brown.cs.counterpointer.notenotation.interval.Intervals;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntervalsInARowRuleTest {
  // Rules
  private final IntervalsInARowRule fourthsAndUnisons
      = new IntervalsInARowRule(1,
      new int[]{3, 0}, 0);
  private final IntervalsInARowRule noSixths
      = new IntervalsInARowRule(0,
      new int[]{5}, 0);
  private final IntervalsInARowRule twoPer4sOffset
      = new IntervalsInARowRule(2,
      new int[]{3}, 2);
  private final IntervalsInARowRule twoPer4s1BeatOffset
      = new IntervalsInARowRule(2,
      new int[]{3}, 1);
  @Test
  public void tritonesAndUnisonsExcluded() {
    // One is okay
    assertTrue(fourthsAndUnisons.passes(TestingExamples.twoQuarterNote,
        TestingExamples.empty,
        TestingExamples.fSharp0Half));
    assertTrue(fourthsAndUnisons.passes(TestingExamples.twoQuarterNote,
        TestingExamples.empty,
        TestingExamples.gSharp0Half));
    assertTrue(fourthsAndUnisons.passes(TestingExamples.twoQuarterNote,
        TestingExamples.empty,
        TestingExamples.c0Quarter));
    assertTrue(fourthsAndUnisons.passes(TestingExamples.twoQuarterNote,
        TestingExamples.empty,
        TestingExamples.c1Quarter));
    // two is not, but it's okay if you're holding down a note
    assertFalse(fourthsAndUnisons.passes(TestingExamples.jumpy,
        TestingExamples.empty,
        TestingExamples.c0Whole));
    assertFalse(fourthsAndUnisons.passes(TestingExamples.conjunctVascillate,
        TestingExamples.twoQuarterNote,
        TestingExamples.c0Quarter));
    // Rests
    assertTrue(fourthsAndUnisons.passes(TestingExamples.halfRests,
        TestingExamples.empty,
        TestingExamples.halfRest));
  }

  @Test
  public void noSixths() {
    // Not applicable to enharmonic equivalents
    assertTrue(noSixths.passes(TestingExamples.conjunctVascillate,
        TestingExamples.oneHalfNote,
        TestingExamples.fSharp0Half));
    // Normal behavior
    assertFalse(noSixths.passes(TestingExamples.conjunctVascillate,
        TestingExamples.oneHalfNote,
        TestingExamples.aFlat0Half));
    assertFalse(noSixths.passes(TestingExamples.oneWholeNote,
        TestingExamples.oneHalfNote,
        TestingExamples.aFlat0Half));
  }

  @Test
  public void offsetTests() {
    assertFalse(twoPer4sOffset.passes(TestingExamples.conjunctVascillate,
        TestingExamples.conjunctVascillateOffset,
        TestingExamples.f0Quarter));
    assertFalse(twoPer4s1BeatOffset.passes(TestingExamples.conjunctVascillate,
        TestingExamples.conjunctVascillateOffset,
        TestingExamples.g0Quarter));
  }
}
