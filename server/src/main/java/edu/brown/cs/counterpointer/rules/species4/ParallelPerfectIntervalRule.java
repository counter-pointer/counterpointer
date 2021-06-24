package edu.brown.cs.counterpointer.rules.species4;

import edu.brown.cs.counterpointer.rules.general.melodytomelody.IntervalsInARowRule;

import java.util.Arrays;

import static edu.brown.cs.counterpointer.notenotation.interval.Intervals.PER1;
import static edu.brown.cs.counterpointer.notenotation.interval.Intervals.PER5;

public class ParallelPerfectIntervalRule extends IntervalsInARowRule {
  /**
   * Class constructor.
   */
  public ParallelPerfectIntervalRule() {
    super(1, new int[]{0, 4}, 2);
  }
}
