package edu.brown.cs.counterpointer.rules.general.melodytomelody;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UnisonRuleTest {
  UnisonRule rule = new UnisonRule();
  @Test
  public void passesTests() {
    // Rests mean no unisons
    assertTrue(rule.passes(TestingExamples.halfRests,
        TestingExamples.twoHalfNote,
        TestingExamples.halfRest));
    // Normal cases
    assertTrue(rule.passes(TestingExamples.conjunctVascillateOffset,
        TestingExamples.twoQuarterNote,
        TestingExamples.c0Quarter));
    assertTrue(rule.passes(TestingExamples.conjunctVascillateOffset,
        TestingExamples.twoQuarterNote,
        TestingExamples.fSharp0Half));
    assertTrue(rule.passes(TestingExamples.conjunctVascillateOffset,
        TestingExamples.twoQuarterNote,
        TestingExamples.fSharp0Half));
    // If a unison is in the middle, this is bad
    assertFalse(rule.passes(TestingExamples.conjunctVascillateOffset,
        TestingExamples.twoQuarterNote,
        TestingExamples.f0Quarter));
    // If the beginnings and ends are unisons, this is fine
    assertTrue(rule.passes(TestingExamples.twoQuarterNote,
        TestingExamples.empty,
        TestingExamples.c0Quarter));
    assertTrue(rule.passes(TestingExamples.twoHalfNote,
        TestingExamples.oneHalfNote,
        TestingExamples.fSharp0Half));
    // Octaves in the middle are okay
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.twoQuarterNote,
        TestingExamples.c1Quarter));
  }
}
