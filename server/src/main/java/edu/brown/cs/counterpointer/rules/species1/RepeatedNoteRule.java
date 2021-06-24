package edu.brown.cs.counterpointer.rules.species1;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.rules.Rule;

import java.util.List;

/**
 * One repeated note is allowed in species counterpoint, per 16 measures.
 */
public class RepeatedNoteRule implements Rule {
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
    List<Note> newNotesList = cm.getNotes();
    newNotesList.add(n);
    int repeatedNoteCount = 0;
    for (int i = 0; i < newNotesList.size() - 1; i++) {
      if (newNotesList.get(i).getPitch() != null && n.getPitch() != null
        && newNotesList.get(i).getPitch().equals(newNotesList.get(i + 1).getPitch())) {
        repeatedNoteCount++;
      }
    }
    return repeatedNoteCount <= (newNotesList.size() / 16) + 1;
  }

  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @Override
  public String name() {
    return "Repeated notes";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "A 16-measure first species counterpoint segment may have no"
      + "more than one repeated note";
  }
}
