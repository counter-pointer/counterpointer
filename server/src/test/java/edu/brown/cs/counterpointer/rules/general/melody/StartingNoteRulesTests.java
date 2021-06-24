package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StartingNoteRulesTests {
  StartingNoteRule rule = new StartingNoteRule();
  @Test
  public void passesTests() {
    // Starting note is the tonic of ionian mode
    assertFalse(rule.passes(TestingExamples.oneHalfNote,
        TestingExamples.empty,
        TestingExamples.c0Quarter));
    // Starting note is the actual tonic
    assertTrue(rule.passes(TestingExamples.oneHalfNote,
        TestingExamples.empty,
        TestingExamples.d0Quarter));
    assertTrue(rule.passes(TestingExamples.oneHalfNote,
        TestingExamples.phrygianMelody,
        TestingExamples.f0Quarter));
    // not in the key
    assertTrue(rule.passes(TestingExamples.oneHalfNote,
        TestingExamples.phrygianMelody,
        TestingExamples.fSharp0Half));
    assertFalse(rule.passes(TestingExamples.oneHalfNote,
        TestingExamples.empty,
        TestingExamples.fSharp0Half));
    // Is not the starting note
    assertTrue(rule.passes(TestingExamples.oneHalfNote,
        TestingExamples.twoHalfNote,
        TestingExamples.d0Quarter));
    // passesUpper()
    assertTrue(rule.passesLower(TestingExamples.conjunctVascillate,
        TestingExamples.empty,
        TestingExamples.a0Quarter));
  }
}
