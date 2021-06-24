package edu.brown.cs.counterpointer.rules.shared;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParallelImperfectIntervalRuleTest {
  ParallelImperfectIntervalRule rule = new ParallelImperfectIntervalRule();
  @Test
  public void passesTests() {
    // more than 3 thirds
    assertFalse(rule.passes(TestingExamples.longMelody2,
        TestingExamples.fourQuarterMelody,
        TestingExamples.f1Quarter));
    assertTrue(rule.passes(TestingExamples.longMelody2,
        TestingExamples.fourQuarterMelody,
        TestingExamples.f0Quarter));
    assertTrue(rule.passes(TestingExamples.longMelody2,
        TestingExamples.twoQuarterMelody,
        TestingExamples.f0Quarter));
    // rests of the same length
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.twoQuarterMelody,
        TestingExamples.f0Quarter));
    // more than 3 sixths
    assertFalse(rule.passes(TestingExamples.quarterMelody,
      TestingExamples.quarterMelody2,
      TestingExamples.e1Quarter));
  }
}
