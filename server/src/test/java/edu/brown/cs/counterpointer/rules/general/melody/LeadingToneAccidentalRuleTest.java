package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LeadingToneAccidentalRuleTest {
  LeadingToneAccidentalRule rule = new LeadingToneAccidentalRule();
  @Test
  public void passesTest() {
    // Correct accidental but incorrect note
    assertFalse(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.oneHalfNote,
        TestingExamples.c0Quarter));
    // Correct accidental and note
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.oneHalfNote,
        TestingExamples.cSharp0Quarter));
    // Incorrect accidental and correct note
    assertFalse(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.oneHalfNote,
        TestingExamples.dSharp0Quarter));
    // Phrygian melody
    assertFalse(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.phrygianMelody,
        TestingExamples.dSharp0Quarter));
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.phrygianMelody,
        TestingExamples.eFlat0Quarter));
  }
}
