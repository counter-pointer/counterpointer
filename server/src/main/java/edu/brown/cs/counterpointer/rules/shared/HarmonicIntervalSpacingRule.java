package edu.brown.cs.counterpointer.rules.shared;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.interval.Interval;
import edu.brown.cs.counterpointer.rules.Rule;

/**
 * Spacing rule for Species 2, 4.
 */
public class HarmonicIntervalSpacingRule implements Rule {

  /**
   * Determines whether appending the given note to the end of
   * the sub-countermelody will result in a rule violation.
   *
   * @param m  Melody
   * @param cm Subsection of countermelody up to a point
   * @param n  Note
   * @return Boolean value
   */
  @Override
  public boolean passes(Melody m, Melody cm, Note n) {
    if (m.length() == 0) {
      return true;
    }
    Interval nextInterval = n.interval(m.at(cm.length()));
    // no interval larger than a 12th is allowed (distance of 11)
    return nextInterval == null || nextInterval.getDistance() < 12;
  }

  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @Override
  public String name() {
    return "Spacing in second and fourth species should not exceed a twelfth";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "The maximum allowed interval between a melody note and a countermelody"
        + " note is an octave plus a fifth";
  }
}
