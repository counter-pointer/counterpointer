package edu.brown.cs.counterpointer.rules.species3;

import edu.brown.cs.counterpointer.notenotation.Duration;
import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.rules.Rule;

import java.util.List;

/**
 * Notes in third species counterpoints are quarters.
 */
public class QuarterNotesRule implements Rule {

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
    List<Note> newCmNotes = cm.getNotes();
    newCmNotes.add(n);
    Melody newCm = new Melody(newCmNotes, cm.getKey(), cm.getUnitBeat());
    // last note must be whole: another rule will catch this
    if (newCm.length() == m.length()) {
      return true;
    }
    return n.getDuration().equals(Duration.QUARTER);
  }

  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @Override
  public String name() {
    return "Not a quarter note";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "In third species, all notes should be quarter notes except for the last note";
  }
}
