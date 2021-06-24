package edu.brown.cs.counterpointer.rules.general.melodytomelody;

import edu.brown.cs.counterpointer.TestingExamples;
import edu.brown.cs.counterpointer.rules.RuleUtilities;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimultaneousClimaxRuleTest {
  SimultaneousClimaxRule rule = new SimultaneousClimaxRule();
  @Test
  public void passesTest() {
    assertFalse(rule.passes(TestingExamples.oneWholeNote,
        TestingExamples.empty,
        TestingExamples.c0Quarter));
    // test that octave doesn't matter
    assertFalse(rule.passes(TestingExamples.oneWholeNote,
        TestingExamples.empty,
        TestingExamples.c1Quarter));
    // rule-passing
    assertTrue(rule.passes(TestingExamples.oneWholeNote,
        TestingExamples.conjunctVascillate,
        TestingExamples.c1Quarter));
  }
}
