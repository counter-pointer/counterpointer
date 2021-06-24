package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DissonantLeapRuleTest {
  DissonantLeapRule rule = new DissonantLeapRule();
  @Test
  public void passesTests() {
    // no leap
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.conjunctVascillate,
        TestingExamples.c0Quarter));
    // has rests
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.twoQuarterNote,
        TestingExamples.quarterRest));
    // consonant leap
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.jumpy,
        TestingExamples.c0Whole));
    // augmented interval
    assertFalse(rule.passes(TestingExamples.halfRests,
        TestingExamples.jumpy,
        TestingExamples.gSharp0Half));
    // 7th
    assertFalse(rule.passes(TestingExamples.halfRests,
        TestingExamples.jumpy,
        TestingExamples.d0Quarter));
    // 9th
    assertFalse(rule.passes(TestingExamples.halfRests,
        TestingExamples.conjunctVascillate,
        TestingExamples.e1Quarter));
  }
}
