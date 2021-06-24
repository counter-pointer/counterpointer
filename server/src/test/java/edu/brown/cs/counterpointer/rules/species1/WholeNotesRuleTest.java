package edu.brown.cs.counterpointer.rules.species1;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WholeNotesRuleTest {
  WholeNotesRule rule = new WholeNotesRule();
  @Test
  public void passesTests() {
    assertTrue(rule.passes(TestingExamples.oneHalfNote,
        TestingExamples.oneWholeNote,
        TestingExamples.d0Whole));
    assertTrue(rule.passes(TestingExamples.oneHalfNote,
        TestingExamples.oneWholeNote,
        TestingExamples.wholeRest));
    assertFalse(rule.passes(TestingExamples.oneHalfNote,
        TestingExamples.oneWholeNote,
        TestingExamples.d0Quarter));
    assertFalse(rule.passes(TestingExamples.oneHalfNote,
        TestingExamples.oneWholeNote,
        TestingExamples.gSharp0Half));
  }
}
