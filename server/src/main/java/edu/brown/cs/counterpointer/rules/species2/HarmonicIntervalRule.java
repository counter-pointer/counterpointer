package edu.brown.cs.counterpointer.rules.species2;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.interval.Interval;
import edu.brown.cs.counterpointer.rules.Rule;
import edu.brown.cs.counterpointer.rules.RuleUtilities;

/**
 * No dissonant intervals on strong beats in species 2
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
    if (m.length() == 0) {
      return true;
    }
    Interval nextInterval = n.interval(m.at(cm.length()));
    return (nextInterval ==  null || (cm.length() % 4 == 0 || cm.length() % 4 == 1)
            && !RuleUtilities.isHarmonicallyDissonant(nextInterval))
        || (cm.length() % 4 == 2 || cm.length() % 4 == 3);
  }

  @Override
  public boolean passesUpper(Melody m, Melody cm, Note n) {
    return passes(m, cm, n);
  }

  @Override
  public boolean passesLower(Melody m, Melody cm, Note n) {
    return passes(m, cm, n);
  }

  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @Override
  public String name() {
    return "No dissonant harmonic intervals";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "No harmonic 4ths, 7ths, augmented/diminished intervals on first beats";
  }
}
