package edu.brown.cs.counterpointer.analysis.generators;

import edu.brown.cs.counterpointer.analysis.checkers.Checker;
import edu.brown.cs.counterpointer.rules.general.melody.*;
import edu.brown.cs.counterpointer.rules.species1.LeapsResolveRule;

import java.util.Arrays;

public class GeneratorTest {

  private final Checker generalMelodyRuleChecker = new Checker(Arrays.asList(
    new ClimaxRule(),
    new DissonantIntervalOutlineRule(),
    new DissonantLeapRule(),
    new EndingNoteIsWholeRule(),
    new EndingNoteRule(),
    new LeadingToneAccidentalRule(),
    new LeapsResolveRule(),
    new StartingNoteRule(),
    new StaticMelodyRule(),
    new ValidKeyRule()));
  private final Generator generalMelodyRuleGenerator = new Generator(generalMelodyRuleChecker);

//  @Test
//  public void generatePossibleNextTests() {
//    assertEquals(Collections.emptyList(),
//      generalMelodyRuleGenerator.generatePossibleNext(new Score(jumpy, empty, true)));
//  }
}
