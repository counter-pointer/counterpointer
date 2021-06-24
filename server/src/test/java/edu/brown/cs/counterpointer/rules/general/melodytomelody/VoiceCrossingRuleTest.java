package edu.brown.cs.counterpointer.rules.general.melodytomelody;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VoiceCrossingRuleTest {
  VoiceCrossingRule rule = new VoiceCrossingRule();
  @Test
  public void passesTest() {
    // voice crossing
    assertFalse(rule.passesLower(TestingExamples.twoHalfNote,
        TestingExamples.oneHalfNote,
        TestingExamples.c0Whole));
    assertFalse(rule.passesLower(TestingExamples.jumpy,
        TestingExamples.oneHalfNote,
        TestingExamples.c0Quarter));
    // same note (should pass)
    assertTrue(rule.passesLower(TestingExamples.conjunctVascillateOffset,
        TestingExamples.oneHalfNote,
        TestingExamples.gSharp0Half));
    assertTrue(rule.passesUpper(TestingExamples.conjunctVascillate,
        TestingExamples.oneWholeNote,
        TestingExamples.c0Quarter));
    // voice overlap
    assertTrue(rule.passesUpper(TestingExamples.twoHalfNote,
        TestingExamples.oneHalfNote,
        TestingExamples.c0Whole));
    assertFalse(rule.passesUpper(TestingExamples.twoHalfNote,
        TestingExamples.empty,
        TestingExamples.gSharp0Half));
    // melodies do not touch
    assertTrue(rule.passesUpper(TestingExamples.conjunctVascillate,
        TestingExamples.oneHalfNote,
        TestingExamples.c0Quarter));
    // rests
    assertTrue(rule.passesLower(TestingExamples.halfRests,
        TestingExamples.empty,
        TestingExamples.gSharp0Half));
    assertTrue(rule.passesUpper(TestingExamples.halfRests,
        TestingExamples.twoHalfNote,
        TestingExamples.fSharp0Half));
  }
}
