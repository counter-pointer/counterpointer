package edu.brown.cs.counterpointer.rules.species4;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.interval.Interval;
import edu.brown.cs.counterpointer.rules.Rule;
import edu.brown.cs.counterpointer.rules.RuleUtilities;

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
    return ((cm.length() % 4 != 3 || cm.length() % 4 != 4)
        && !RuleUtilities.isHarmonicallyDissonant(nextInterval))
        || (cm.length() % 4 == 3 || cm.length() % 4 == 4)
        // no interval larger than a 12th is allowed (distance of 11)
        && nextInterval.getDistance() < 12;
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
    return "Harmonic interval rules for fourth species counterpoint";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "No harmonic 4ths, 7ths, augmented/diminished intervals,"
        + "or intervals greater than a twelfth.";
  }
}
