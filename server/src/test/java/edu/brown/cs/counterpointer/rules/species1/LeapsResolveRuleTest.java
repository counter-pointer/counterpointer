package edu.brown.cs.counterpointer.rules.species1;

import edu.brown.cs.counterpointer.TestingExamples;
import edu.brown.cs.counterpointer.rules.species1.LeapsResolveRule;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LeapsResolveRuleTest {
  LeapsResolveRule rule = new LeapsResolveRule();
  @Test
  public void passesTests() {
    // leap but not resolved yet bc you haven't gotten there
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.conjunctVascillate,
        TestingExamples.c1Quarter));
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.fifthLeap,
        TestingExamples.a0Quarter));
    // correct leap resolution
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.jumpy,
        TestingExamples.b0Quarter));
    // step
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.conjunctVascillate,
        TestingExamples.c0Quarter));
    // rest followed by rest
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.halfRests,
        TestingExamples.quarterRest));
    // rest followed by note
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.halfRests,
        TestingExamples.c0Whole));
  }

  @Test
  public void failingLeapTests() {
    // leaps up
    assertFalse(rule.passes(TestingExamples.halfRests,
        TestingExamples.jumpy,
        TestingExamples.c0Quarter));
    assertFalse(rule.passes(TestingExamples.halfRests,
        TestingExamples.jumpy,
        TestingExamples.f1Quarter));
    // leaps down
    assertFalse(rule.passes(TestingExamples.halfRests,
        TestingExamples.fifthLeap,
        TestingExamples.d0Quarter));
    assertFalse(rule.passes(TestingExamples.halfRests,
        TestingExamples.fifthLeap,
        TestingExamples.e0Quarter));
    assertFalse(rule.passes(TestingExamples.halfRests,
        TestingExamples.fifthLeap,
        TestingExamples.f0Quarter));
    assertFalse(rule.passes(TestingExamples.halfRests,
        TestingExamples.fifthLeap,
        TestingExamples.g0Quarter));
    assertFalse(rule.passes(TestingExamples.halfRests,
        TestingExamples.fifthLeap,
        TestingExamples.d1Quarter));
    assertFalse(rule.passes(TestingExamples.halfRests,
        TestingExamples.fifthLeap,
        TestingExamples.c1Quarter));
    assertFalse(rule.passes(TestingExamples.halfRests,
        TestingExamples.ascFifth,
        TestingExamples.a1Quarter));
    // octave matters
    assertFalse(rule.passes(TestingExamples.halfRests,
        TestingExamples.fifthLeap,
        TestingExamples.bNeg1Quarter));
  }
}
