package edu.brown.cs.counterpointer.analysis.checkers;

import edu.brown.cs.counterpointer.rules.general.melody.*;
import edu.brown.cs.counterpointer.rules.general.melodytomelody.*;
import edu.brown.cs.counterpointer.rules.shared.*;
import edu.brown.cs.counterpointer.rules.species1.*;
import edu.brown.cs.counterpointer.rules.species1.InterestingMelodyRule;
import edu.brown.cs.counterpointer.rules.species2.HalfNotesRule;
import edu.brown.cs.counterpointer.rules.species3.NotTooDisjunctRule;
import edu.brown.cs.counterpointer.rules.species3.ParallelPerfectBeginningOfMeasureRule;
import edu.brown.cs.counterpointer.rules.species3.QuarterNotesRule;

import java.util.ArrayList;
import java.util.Arrays;

public final class Checkers {
  /**
   * Disable constructor.
   */
  private Checkers() {
    throw new AssertionError("Instantiating static class");
  }

  public static final Checker species1 = new Checker(Arrays.asList(
      new WholeNotesRule(),
      new NoRestRule(),
      new ValidKeyRule(),
      new SpacingRule(),
      new HarmonicIntervalRule(),
      new DissonantLeapRule(),
      new EndingNoteIsWholeRule(),
      new EndingNoteRule(),
      new LeapsResolveRule(),
      new StartingNoteRule(),
      new StaticMelodyRule(),
      new UnisonRule(),
      new ParallelImperfectIntervalRule(),
      new ParallelPerfectIntervalRule(),
      new RepeatedNoteRule(),
      new VoiceCrossingRule(),
      new LeadingToneAccidentalRule(),
      new ClimaxCmEndReviewRule(),
      new InterestingMelodyRule(),
      new DissonantIntervalOutlineRule(),
      new NoLeapsGreaterThanAnOctaveRule()
  ));

  public static final Checker species2 = new Checker(Arrays.asList(
      new HalfNotesRule(),
      new NoRestExceptAtBeginningRule(),
      new ValidKeyRule(),
      new HarmonicIntervalSpacingRule(),
      new edu.brown.cs.counterpointer.rules.species2.HarmonicIntervalRule(),
      new DissonantLeapRule(),
      new EndingNoteIsWholeRule(),
      new EndingNoteRule(),
      new StartingNoteRule(),
      new StaticMelodyRule(),
      new UnisonRule(),
      new RepeatedNotesRule(),
      new VoiceCrossingRule(),
      new LeadingToneAccidentalRule(),
      new ClimaxCmEndReviewRule(),
      new edu.brown.cs.counterpointer.rules.species2.InterestingMelodyRule(),
      new DissonantIntervalOutlineRule(),
      new ParallelImperfectEveryOtherRule(),
      new ParallelPerfectEveryOtherRule(),
      new ParallelPerfectIntervalRule(),
      new NoLeapsGreaterThanAnOctaveRule()
  ));

  public static final Checker species3 = new Checker(Arrays.asList(
      new QuarterNotesRule(),
      new NoRestExceptAtBeginningRule(),
      new ValidKeyRule(),
      new edu.brown.cs.counterpointer.rules.species3.SpacingRule(),
      new edu.brown.cs.counterpointer.rules.species3.HarmonicIntervalRule(),
      new DissonantLeapRule(),
      new EndingNoteIsWholeRule(),
      new EndingNoteRule(),
      new StartingNoteRule(),
      new StaticMelodyRule(),
      new UnisonRule(),
      new RepeatedNotesRule(),
      new VoiceCrossingRule(),
      new LeadingToneAccidentalRule(),
      new ClimaxCmEndReviewRule(),
      new edu.brown.cs.counterpointer.rules.species3.InterestingMelodyRule(),
      new DissonantIntervalOutlineRule(),
      new ParallelPerfectEveryOtherRule(),
      new ParallelPerfectIntervalRule(),
      new ParallelPerfectBeginningOfMeasureRule(),
      new NoLeapsGreaterThanAnOctaveRule(),
      new NotTooDisjunctRule(),
      new LeapsResolveRule()
  ));

  public static final Checker species4 = new Checker(new ArrayList<>(

  ));
}