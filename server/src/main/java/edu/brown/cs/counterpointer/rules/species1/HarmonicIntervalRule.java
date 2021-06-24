package edu.brown.cs.counterpointer.rules.species1;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.interval.Interval;
import edu.brown.cs.counterpointer.rules.Rule;
import edu.brown.cs.counterpointer.rules.RuleUtilities;

/**
 * No dissonant intervals are allowed in species 1.
 */
public class HarmonicIntervalRule implements Rule {
  public HarmonicIntervalRule() { }

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
    return !RuleUtilities.isHarmonicallyDissonant(nextInterval);
  }

  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @Override
  public String name() {
    return "Dissonant harmonic interval";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "No harmonic 4ths, 7ths, or augmented/diminished intervals";
  }
}
