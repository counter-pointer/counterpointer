package edu.brown.cs.counterpointer.rules.species1;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.rules.Rule;

/**
 * No rests are allowed in first species.
 */
public class NoRestRule implements Rule {

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
    return !n.isRest();
  }

  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @Override
  public String name() {
    return "No rests allowed";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "All notes must be notes in first species counterpoint";
  }
}
