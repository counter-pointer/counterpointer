package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EndingNoteRuleTest {
  EndingNoteRule rule = new EndingNoteRule();
  @Test
  public void passesTests() {
    // incomplete melody not ending on the tonic
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.oneHalfNote,
        TestingExamples.c0Quarter));
    // complete melody not ending on the tonic
    assertFalse(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.oneHalfNote,
        TestingExamples.fSharp0Half));
    assertFalse(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.oneHalfNote,
        TestingExamples.halfRest));
    // complete melody ending on the tonic
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.oneHalfNote,
        TestingExamples.d0Whole));
  }
}
