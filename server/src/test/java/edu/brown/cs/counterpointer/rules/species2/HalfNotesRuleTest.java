package edu.brown.cs.counterpointer.rules.species2;

import edu.brown.cs.counterpointer.TestingExamples;
import edu.brown.cs.counterpointer.rules.Rule;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HalfNotesRuleTest {
  Rule rule = new HalfNotesRule();
  @Test
  public void passesTest() {
    assertTrue(rule.passes(TestingExamples.oneHalfNote,
        TestingExamples.oneWholeNote,
        TestingExamples.gSharp0Half));
    assertTrue(rule.passes(TestingExamples.oneHalfNote,
        TestingExamples.oneWholeNote,
        TestingExamples.halfRest));
    assertFalse(rule.passes(TestingExamples.longMelody,
        TestingExamples.empty,
        TestingExamples.c0Whole));
    assertFalse(rule.passes(TestingExamples.twoWholeNotes,
        TestingExamples.oneWholeNote,
        TestingExamples.c0Quarter));
    // second to last bar can have whole or half
    assertTrue(rule.passes(TestingExamples.twoWholeNotes,
        TestingExamples.empty,
        TestingExamples.c0Whole));
    assertTrue(rule.passes(TestingExamples.twoWholeNotes,
        TestingExamples.empty,
        TestingExamples.a0Half));
    assertFalse(rule.passes(TestingExamples.twoWholeNotes,
        TestingExamples.empty,
        TestingExamples.a0Quarter));
  }
}
