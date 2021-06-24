package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InterestingMelodyTest {
  InterestingMelodyRule oneChangePerMeasure =
      new InterestingMelodyRule(1, 1);
  InterestingMelodyRule noChanges =
      new InterestingMelodyRule(0, 1);
  InterestingMelodyRule threeChangesPerEightMeasures =
      new InterestingMelodyRule(3, 8);
  InterestingMelodyRule impossibleRule =
      new InterestingMelodyRule(4, 1);
  @Test
  public void passesTests() {
    // A whole note does not change direction
    assertFalse(oneChangePerMeasure.passes(TestingExamples.oneWholeNote,
        TestingExamples.oneWholeNote,
        TestingExamples.c0Whole));
    // Works on empty cms
    assertFalse(oneChangePerMeasure.passes(TestingExamples.oneWholeNote,
        TestingExamples.empty,
        TestingExamples.c0Whole));
    // a melody that goes back down
    assertTrue(oneChangePerMeasure.passes(TestingExamples.oneWholeNote,
        TestingExamples.threeNoteMelody,
        TestingExamples.c0Quarter));
    assertFalse(oneChangePerMeasure.passes(TestingExamples.oneWholeNote,
        TestingExamples.threeNoteMelody,
        TestingExamples.c0Whole));
    // A melody that only goes up
    assertFalse(oneChangePerMeasure.passes(TestingExamples.oneWholeNote,
        TestingExamples.threeNoteMelody,
        TestingExamples.g0Quarter));
    // No changes rule
    assertTrue(noChanges.passes(TestingExamples.oneWholeNote,
        TestingExamples.threeNoteMelody,
        TestingExamples.g0Quarter));
    // 3 changes per 8 measures
    assertTrue(threeChangesPerEightMeasures.passes(TestingExamples.oneWholeNote,
        TestingExamples.threeNoteMelody,
        TestingExamples.g0Quarter));
    // impossible demands
    assertFalse(impossibleRule.passes(TestingExamples.oneWholeNote,
        TestingExamples.twoQuarterNote,
        TestingExamples.c0Quarter));
  }
}
