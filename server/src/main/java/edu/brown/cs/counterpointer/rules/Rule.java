package edu.brown.cs.counterpointer.rules;

import com.fasterxml.jackson.annotation.JsonGetter;
import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;

/**
 * Interface for rules.
 */
public interface Rule {

  /**
   * Determines whether appending the given note to the end of
   * the sub-countermelody will result in a rule violation.
   *
   * @param m  Melody
   * @param cm Subsection of countermelody up to a point
   * @param n  Note
   * @return Boolean value
   */
  boolean passes(Melody m, Melody cm, Note n);

  default boolean passesUpper(Melody m, Melody cm, Note n) {
    return passes(m, cm, n);
  };

  default boolean passesLower(Melody m, Melody cm, Note n) {
    return passes(m, cm, n);
  };

  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @JsonGetter("name")
  String name();

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @JsonGetter("description")
  String description();
}
