package edu.brown.cs.counterpointer.rules.shared;

import edu.brown.cs.counterpointer.rules.general.melodytomelody.IntervalsInARowRule;

public class ParallelImperfectIntervalRule extends IntervalsInARowRule {
  /**
   * No more than 3 parallel imperfect consonant intervals in a row.
   */
  public ParallelImperfectIntervalRule() {
    super(3, new int[]{2, 5}, 0);
  }
}
