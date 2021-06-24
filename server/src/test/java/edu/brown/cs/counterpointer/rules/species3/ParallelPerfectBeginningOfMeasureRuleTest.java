package edu.brown.cs.counterpointer.rules.species3;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParallelPerfectBeginningOfMeasureRuleTest {
  ParallelPerfectBeginningOfMeasureRule rule = new ParallelPerfectBeginningOfMeasureRule();
  @Test
  public void passesTests() {
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
      TestingExamples.threeNoteMelody,
      TestingExamples.f0Quarter));
    assertTrue(rule.passes(TestingExamples.fourQuarterMelody,
      TestingExamples.twoQuarterMelody,
      TestingExamples.d1Quarter));
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
      TestingExamples.twoNoteMelody2,
      TestingExamples.g0Quarter));
    assertFalse(rule.passes(TestingExamples.twoWhole,
      TestingExamples.fourQuarterMelody2,
      TestingExamples.a0Quarter));
  }
}
