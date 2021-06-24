package edu.brown.cs.counterpointer.rules.shared;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParallelPerfectEveryOtherRuleTest {
  ParallelPerfectEveryOtherRule rule = new ParallelPerfectEveryOtherRule();
  @Test
  public void passesTests() {
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.threeNoteMelody,
        TestingExamples.f0Quarter));
    assertFalse(rule.passes(TestingExamples.fourQuarterMelody,
        TestingExamples.twoQuarterMelody,
        TestingExamples.d1Quarter));
    assertFalse(rule.passes(TestingExamples.conjunctVascillate,
      TestingExamples.twoNoteMelody2,
      TestingExamples.g0Quarter));
    assertFalse(rule.passes(TestingExamples.twoWhole,
      TestingExamples.twoHalf,
      TestingExamples.a0Half));
  }
}
