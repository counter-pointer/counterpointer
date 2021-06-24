package edu.brown.cs.counterpointer.rules.species3;

import edu.brown.cs.counterpointer.TestingExamples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HarmonicIntervalRuleTest {
  HarmonicIntervalRule rule = new HarmonicIntervalRule();
  @Test
  public void passesTests() {
    // consonant intervals
    assertTrue(rule.passes(TestingExamples.longMelody2,
      TestingExamples.fourQuarterMelody,
      TestingExamples.f1Quarter));
    // rests
    assertTrue(rule.passes(TestingExamples.halfRests,
      TestingExamples.empty,
      TestingExamples.f1Quarter));
    // dissonance on the first beat
    assertFalse(rule.passes(TestingExamples.longMelody2,
      TestingExamples.empty,
      TestingExamples.b0Quarter));
    assertFalse(rule.passes(TestingExamples.longMelody2,
      TestingExamples.empty,
      TestingExamples.f0Quarter));
    // dissonance on the third beat
    assertFalse(rule.passes(TestingExamples.longMelody2,
      TestingExamples.twoQuarterMelody,
      TestingExamples.gSharp0Half));
    // dissonance on a weak beat
    assertTrue(rule.passes(TestingExamples.longMelody2,
      TestingExamples.threeNoteMelody,
      TestingExamples.a0Quarter));
  }
}
