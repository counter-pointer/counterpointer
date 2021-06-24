package edu.brown.cs.counterpointer.rules.species3;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuarterNotesRuleTest {
  QuarterNotesRule rule = new QuarterNotesRule();
  @Test
  public void passesTest() {
    assertTrue(rule.passes(TestingExamples.oneWholeNote,
      TestingExamples.empty,
      TestingExamples.c0Whole));
    assertTrue(rule.passes(TestingExamples.twoWholeNotes,
      TestingExamples.threeNoteMelody,
      TestingExamples.c0Quarter));
    assertFalse(rule.passes(TestingExamples.twoWholeNotes,
      TestingExamples.threeNoteMelody,
      TestingExamples.g0Half));
  }
}
