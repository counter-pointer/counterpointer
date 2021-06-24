package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClimaxRuleTest {
  ClimaxRule rule = new ClimaxRule();
  @Test
  public void passesTest() {
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.oneHalfNote,
        TestingExamples.d0Quarter));
    // same climax
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.empty,
        TestingExamples.d0Quarter));
    // leading tone can be included in the melody, just not as the climax
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.oneHalfNote,
        TestingExamples.c0Quarter));
    // what happens when the leading tone is the climax
    assertFalse(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.empty,
        TestingExamples.c0Quarter));
  }
}
