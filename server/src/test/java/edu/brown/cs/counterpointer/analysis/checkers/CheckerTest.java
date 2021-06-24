package edu.brown.cs.counterpointer.analysis.checkers;

import edu.brown.cs.counterpointer.analysis.generators.Generators;
import edu.brown.cs.counterpointer.rules.general.melody.DissonantIntervalOutlineRule;
import edu.brown.cs.counterpointer.rules.general.melody.DissonantLeapRule;
import edu.brown.cs.counterpointer.rules.general.melody.LeadingToneAccidentalRule;
import edu.brown.cs.counterpointer.rules.general.melody.ValidKeyRule;
import edu.brown.cs.counterpointer.rules.shared.ParallelImperfectIntervalRule;
import edu.brown.cs.counterpointer.rules.species1.HarmonicIntervalRule;
import edu.brown.cs.counterpointer.rules.species1.LeapsResolveRule;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static edu.brown.cs.counterpointer.CheckerTestingExamples.*;
import static org.junit.Assert.assertEquals;


public class CheckerTest {
  @Test
  public void checkTest() {
    // Incorrect scores
    assertEquals(Checkers.species1.check(incorrectAeolianLowerScore),  Arrays.asList(
        new RuleViolation(new ParallelImperfectIntervalRule(), 16),
        new RuleViolation(new DissonantIntervalOutlineRule(), 28)));
    assertEquals(Checkers.species1.check(outline7thScore), Arrays.asList(
        new RuleViolation(new DissonantIntervalOutlineRule(), 24),
        new RuleViolation(new LeapsResolveRule(), 28),
        new RuleViolation(new DissonantIntervalOutlineRule(), 32)));
    assertEquals(Checkers.species1.check(incorrectPhrygianScore), Arrays.asList(
        new RuleViolation(new ValidKeyRule(), 16),
        new RuleViolation(new HarmonicIntervalRule(), 16),
        new RuleViolation(new DissonantLeapRule(), 16),
        new RuleViolation(new LeadingToneAccidentalRule(), 16)));
    assertEquals(Checkers.species1.check(incorrectGAeolianScore), Collections.singletonList(
        new RuleViolation(new LeapsResolveRule(), 8)
    ));

    // Correct scores
    assertEquals(Checkers.species1.check(correctMixolydianScore), Collections.emptyList());
    assertEquals(Checkers.species1.check(correctAeolianUpperScore), Collections.emptyList());
    assertEquals(Checkers.species1.check(correctAeolianLowerScore), Collections.emptyList());
    assertEquals(Checkers.species1.check(correctPhrygianScore), Collections.emptyList());

    System.out.println(Generators.species1.generateCounterMelody(incompleteAeolianScore).getNotes());
  }
}
