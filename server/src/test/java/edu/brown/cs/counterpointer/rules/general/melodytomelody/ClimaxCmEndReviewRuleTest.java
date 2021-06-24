package edu.brown.cs.counterpointer.rules.general.melodytomelody;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClimaxCmEndReviewRuleTest {
  ClimaxCmEndReviewRule rule = new ClimaxCmEndReviewRule();
  @Test
  public void passesTests() {
    // The cm could potentially have a higher note later on
    assertTrue(rule.passes(TestingExamples.oneWholeNote,
        TestingExamples.empty,
        TestingExamples.c0Quarter));
    assertFalse(rule.passes(TestingExamples.oneWholeNote,
        TestingExamples.empty,
        TestingExamples.c0Whole));
    // test that octave doesn't matter
    assertTrue(rule.passes(TestingExamples.oneWholeNote,
        TestingExamples.empty,
        TestingExamples.c1Quarter));
    // Vacillating melody
    assertTrue(rule.passes(TestingExamples.oneWholeNote,
        TestingExamples.conjunctVascillate,
        TestingExamples.c1Quarter));
    assertFalse(rule.passes(TestingExamples.threeNoteMelody,
        TestingExamples.twoQuarterMelody,
        TestingExamples.b0Quarter));
  }
}
