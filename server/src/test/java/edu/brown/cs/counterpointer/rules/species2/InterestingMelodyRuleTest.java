package edu.brown.cs.counterpointer.rules.species2;

import edu.brown.cs.counterpointer.TestingExamples;
import edu.brown.cs.counterpointer.rules.RuleUtilities;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InterestingMelodyRuleTest {
  InterestingMelodyRule rule = new InterestingMelodyRule();
  @Test
  public void passesTests() {
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.sevenNoteNotStatic,
        TestingExamples.c0Quarter));
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.philipGlass,
        TestingExamples.a0Quarter));
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.sevenNoteStatic,
        TestingExamples.c0Quarter));
    assertTrue(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.philipGlass,
        TestingExamples.a0Quarter));
    assertFalse(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.fifteenSecondsPerNote,
        TestingExamples.c0Whole));
    assertFalse(rule.passes(TestingExamples.conjunctVascillate,
        TestingExamples.fifteenSecondsPerNote,
        TestingExamples.gSharp0Half));
  }
}
