package edu.brown.cs.counterpointer.rules.shared;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.exceptions.BeatOutOfBoundsException;
import edu.brown.cs.counterpointer.rules.Rule;

import java.util.List;

/**
 * No repeated notes are allowed for species 2, 3, 4.
 */
public class RepeatedNotesRule implements Rule {
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
    try {
      Note prevNote = cm.getNotes().get(cm.getNotes().size() - 1);
      return ((n.isRest() || prevNote.isRest())
          || !(n.getPitch().equals(prevNote.getPitch())));
    } catch (IndexOutOfBoundsException i) {
      // There was no previous pitch; ie. n will be the first note of the countermelody
      return true;
    }
  }

  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @Override
  public String name() {
    return "No repeated notes allowed";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "No two consecutive notes may have the same pitch";
  }
}
