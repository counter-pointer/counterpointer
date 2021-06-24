package edu.brown.cs.counterpointer.rules.shared;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.rules.Rule;

/**
 * No rests are allowed in species 2 & 3 except at the beginning
 */
public class NoRestExceptAtBeginningRule implements Rule {

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
    return cm.length() == 0 || !n.isRest();
  }

  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @Override
  public String name() {
    return "Rests after the beginning of the countermelody are not permitted";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "No rests are allowed except at the beginning for"
        + "seconds and third species counterpoint";
  }
}
