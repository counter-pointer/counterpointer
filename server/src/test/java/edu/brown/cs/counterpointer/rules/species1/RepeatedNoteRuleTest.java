package edu.brown.cs.counterpointer.rules.species1;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RepeatedNoteRuleTest {
  RepeatedNoteRule rule = new RepeatedNoteRule();
  @Test
  public void passesTests() {
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.fourQuarterMelody,
        TestingExamples.gSharp0Half));
    assertTrue(rule.passes(TestingExamples.empty,
        TestingExamples.fourQuarterMelody,
        TestingExamples.e1Quarter));
    assertFalse(rule.passes(TestingExamples.empty,
        TestingExamples.philipGlass,
        TestingExamples.a0Quarter));
    // empty melody
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.empty,
        TestingExamples.e1Quarter));
    // rests
    assertTrue(rule.passes(TestingExamples.oneWholeNote,
        TestingExamples.halfRests,
        TestingExamples.quarterRest));
  }
}
