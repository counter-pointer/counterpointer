package edu.brown.cs.counterpointer.rules.species3;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.interval.Interval;
import edu.brown.cs.counterpointer.rules.Rule;
import edu.brown.cs.counterpointer.rules.RuleUtilities;

/**
 * No dissonant intervals on strong beats.
 */
public class HarmonicIntervalRule implements Rule {

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
    Interval nextInterval = n.interval(m.at(cm.length()));
    return ((cm.length() % 4 == 0 || cm.length() % 4 == 2)
      && !RuleUtilities.isHarmonicallyDissonant(nextInterval))
      || (cm.length() % 4 == 1 || cm.length() % 4 == 3);
  }

  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @Override
  public String name() {
    return "No dissonant intervals allowed on strong beats (1 and 3) in third species";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "No harmonic 2nds, 4ths, 7ths, augmented/diminished intervals on beats 1 and 3";
  }
}
