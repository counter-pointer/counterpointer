package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EndingNoteIsWholeRuleTest {
  EndingNoteIsWholeRule rule = new EndingNoteIsWholeRule();
  @Test
  public void passesTests() {
    // incomplete melody not ending on the tonic
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.oneHalfNote,
        TestingExamples.c0Quarter));
    // complete melody not ending on a whole note
    assertFalse(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.oneHalfNote,
        TestingExamples.fSharp0Half));
    // tthe note coinciding with the last note of this melody is not whole
    assertFalse(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.oneHalfNote,
        TestingExamples.wholeRest));
    // complete melody ending on a whole note but the whole note isn't lined up right
    assertFalse(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.oneHalfNote,
        TestingExamples.d0Whole));
    // whole note lined up right
    assertTrue(rule.passes(TestingExamples.oneHalfNote,
        TestingExamples.empty,
        TestingExamples.d0Whole));
  }
}
