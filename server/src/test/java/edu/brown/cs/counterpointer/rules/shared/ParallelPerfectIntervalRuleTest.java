package edu.brown.cs.counterpointer.rules.shared;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParallelPerfectIntervalRuleTest {
  ParallelPerfectIntervalRule rule = new ParallelPerfectIntervalRule();
  @Test
  public void passesTest() {
    // no fifths
    assertTrue(rule.passes(TestingExamples.longMelody,
        TestingExamples.longMelody2,
        TestingExamples.f1Quarter));
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.longMelody2,
        TestingExamples.c0Quarter));
    // one fifth
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.empty,
        TestingExamples.g0Quarter));
    // two unisons
    assertFalse(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.twoQuarterNote,
        TestingExamples.c0Quarter));
    assertFalse(rule.passes(TestingExamples.fourthLeap,
        TestingExamples.aMelody,
        TestingExamples.d1Quarter));
  }
}
