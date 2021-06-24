package edu.brown.cs.counterpointer.rules.shared;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParallelImperfectIntervalEveryOtherRuleTest {
  ParallelImperfectEveryOtherRule rule = new ParallelImperfectEveryOtherRule();
  @Test
  public void passesTest() {
    // short melody
    assertTrue(rule.passes(TestingExamples.empty,
        TestingExamples.longMelody2,
        TestingExamples.c0Quarter));
    // no sixths
    assertTrue(rule.passes(TestingExamples.halfRests,
      TestingExamples.longMelody2,
      TestingExamples.c0Quarter));
    // one third
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
      TestingExamples.empty,
      TestingExamples.e0Quarter));
    // four sixths
    assertFalse(rule.passes(TestingExamples.longMelody,
        TestingExamples.longMelody3,
        TestingExamples.e1Quarter));
  }
}
