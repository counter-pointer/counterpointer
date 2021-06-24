package edu.brown.cs.counterpointer.rules.species3;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SpacingRuleTest {
  SpacingRule rule = new SpacingRule();
  @Test
  public void passesTest() {
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
      TestingExamples.empty,
      TestingExamples.c1Quarter));
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
      TestingExamples.empty,
      TestingExamples.g1Quarter));
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
      TestingExamples.empty,
      TestingExamples.a1Quarter));
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
      TestingExamples.empty,
      TestingExamples.c2Quarter));
    assertFalse(rule.passes(TestingExamples.conjunctVascillate,
      TestingExamples.empty,
      TestingExamples.d2Quarter));
  }
}
