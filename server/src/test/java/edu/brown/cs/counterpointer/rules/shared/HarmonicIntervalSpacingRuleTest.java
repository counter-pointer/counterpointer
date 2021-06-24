package edu.brown.cs.counterpointer.rules.shared;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HarmonicIntervalSpacingRuleTest {
  HarmonicIntervalSpacingRule rule = new HarmonicIntervalSpacingRule();
  @Test
  public void passesTests() {
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.empty,
        TestingExamples.c1Quarter));
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.empty,
        TestingExamples.g1Quarter));
    assertFalse(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.empty,
        TestingExamples.a1Quarter));
  }
}
