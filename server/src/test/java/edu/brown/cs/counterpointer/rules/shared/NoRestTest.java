package edu.brown.cs.counterpointer.rules.shared;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NoRestTest {
  NoRestExceptAtBeginningRule rule = new NoRestExceptAtBeginningRule();
  @Test
  public void passesTests() {
    assertFalse(rule.passes(TestingExamples.threeNoteMelody,
        TestingExamples.fifteenSecondsPerNote,
        TestingExamples.wholeRest));
    assertTrue(rule.passes(TestingExamples.threeNoteMelody,
        TestingExamples.fifteenSecondsPerNote,
        TestingExamples.c0Whole));
    assertFalse(rule.passes(TestingExamples.threeNoteMelody,
        TestingExamples.fifteenSecondsPerNote,
        TestingExamples.quarterRest));
    assertTrue(rule.passes(TestingExamples.threeNoteMelody,
        TestingExamples.fifteenSecondsPerNote,
        TestingExamples.f0Quarter));
  }
}
