package edu.brown.cs.counterpointer.rules.shared;

import edu.brown.cs.counterpointer.rules.general.melodytomelody.IntervalsInARowRule;

import java.util.Arrays;

import static edu.brown.cs.counterpointer.notenotation.interval.Intervals.PER1;
import static edu.brown.cs.counterpointer.notenotation.interval.Intervals.PER5;

/**
 * No more than 1 parallel perfect consonant intervals in a row.
 */
public class ParallelPerfectIntervalRule extends IntervalsInARowRule {
  /**
   * Class constructor.
   */
  public ParallelPerfectIntervalRule() {
    super(1, new int[]{4, 0}, 0);
  }
}
