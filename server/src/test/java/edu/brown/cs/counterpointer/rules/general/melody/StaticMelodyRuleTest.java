package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.TestingExamples;
import edu.brown.cs.counterpointer.rules.RuleUtilities;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StaticMelodyRuleTest {
  StaticMelodyRule rule = new StaticMelodyRule();
  @Test
  public void passesTest() {
    assertTrue(rule.passes(TestingExamples.empty,
        TestingExamples.conjunctVascillate,
        TestingExamples.c0Quarter));
    assertTrue(rule.passes(TestingExamples.empty,
        TestingExamples.sevenNoteNotStatic,
        TestingExamples.c0Quarter));
    assertFalse(rule.passes(TestingExamples.empty,
        TestingExamples.philipGlass,
        TestingExamples.a0Quarter));
    assertFalse(rule.passes(TestingExamples.empty,
        TestingExamples.sevenNoteStatic,
        TestingExamples.c0Quarter));
  }
}
