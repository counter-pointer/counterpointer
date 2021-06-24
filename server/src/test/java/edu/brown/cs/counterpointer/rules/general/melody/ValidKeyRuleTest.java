package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidKeyRuleTest {
  ValidKeyRule rule = new ValidKeyRule();
  @Test
  public void passesTest() {
    /*
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.conjunctVascillate,
        TestingExamples.d0Quarter));
    assertFalse(rule.passes(TestingExamples.halfRests,
        TestingExamples.conjunctVascillate,
        TestingExamples.gSharp0Half));
    assertFalse(rule.passes(TestingExamples.halfRests,
        TestingExamples.phrygianMelody,
        TestingExamples.gSharp0Half));
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.phrygianMelody,
        TestingExamples.f0Quarter));
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.phrygianMelody,
        TestingExamples.quarterRest));
    // Leading tone is in the key
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.mixolydianMelody,
        TestingExamples.fSharp0Quarter));
    // Flatted leading tone is not still in the key
    assertFalse(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.mixolydianMelody,
        TestingExamples.f0Quarter));
    */
    // sharped sixth tone is in the key
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.aeolianOneQuarter,
        TestingExamples.fSharp0Quarter));
  }
}
