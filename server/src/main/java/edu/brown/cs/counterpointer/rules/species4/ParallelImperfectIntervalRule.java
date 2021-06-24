package edu.brown.cs.counterpointer.rules.species4;

import edu.brown.cs.counterpointer.rules.general.melodytomelody.IntervalsInARowRule;

import java.util.Arrays;

import static edu.brown.cs.counterpointer.notenotation.interval.Intervals.*;

public class ParallelImperfectIntervalRule extends IntervalsInARowRule {
  /**
   * Class constructor.
   */
  public ParallelImperfectIntervalRule() {
    super(3, new int[]{2, 5}, 2);
  }
}
