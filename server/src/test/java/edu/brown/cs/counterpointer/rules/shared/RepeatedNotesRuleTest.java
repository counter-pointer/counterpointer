package edu.brown.cs.counterpointer.rules.shared;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RepeatedNotesRuleTest {
  RepeatedNotesRule rule = new RepeatedNotesRule();
  @Test
  public void passesTests() {
    assertTrue(rule.passes(TestingExamples.longMelody2,
        TestingExamples.twoQuarterNote,
        TestingExamples.f0Quarter));
    assertFalse(rule.passes(TestingExamples.empty,
        TestingExamples.oneHalfNote,
        TestingExamples.gSharp0Half));
    assertTrue(rule.passes(TestingExamples.longMelody,
        TestingExamples.halfRests,
        TestingExamples.quarterRest));
  }
}
