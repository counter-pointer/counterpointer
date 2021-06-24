package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DissonantIntervalOutlineRuleTest {
  DissonantIntervalOutlineRule rule = new DissonantIntervalOutlineRule();
  @Test
  public void passesTests() {
    // steps
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.conjunctVascillate,
        TestingExamples.c0Quarter));
    // leaps
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.jumpy,
        TestingExamples.c0Quarter));
    // dissonant leaps (we don't know if it will outline an interval yet)
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.conjunctVascillate,
        TestingExamples.c1Quarter));
    // What could've been a dissonant outline if the melody had gone down at note n
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.dissonanceOutline,
        TestingExamples.g0Quarter));
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.longMelody,
        TestingExamples.c1Quarter));
    // dissonant outlines
    assertFalse(rule.passes(TestingExamples.halfRests,
        TestingExamples.dissonanceOutline,
        TestingExamples.d0Quarter));
    assertFalse(rule.passes(TestingExamples.halfRests,
        TestingExamples.longMelody,
        TestingExamples.d0Quarter));
  }
}
