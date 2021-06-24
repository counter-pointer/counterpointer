package edu.brown.cs.counterpointer.rules.species1;

import edu.brown.cs.counterpointer.notenotation.Duration;
import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.rules.Rule;

/**
 * All notes must be whole in first species.
 */
public class WholeNotesRule implements Rule {
  public WholeNotesRule() { }

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
    return n.getDuration().equals(Duration.WHOLE);
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
    return "Non-whole note";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "In first species, all notes must be whole notes.";
  }
}
