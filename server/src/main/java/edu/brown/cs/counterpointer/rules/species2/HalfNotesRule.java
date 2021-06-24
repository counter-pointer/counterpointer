package edu.brown.cs.counterpointer.rules.species2;

import edu.brown.cs.counterpointer.notenotation.Duration;
import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.rules.Rule;

import java.util.List;

/**
 * All notes must be half notes.
 */
public class HalfNotesRule implements Rule {
  public HalfNotesRule() { }

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
    // bar before the last can have either half or whole notes
    if (newCm.length() == (m.length() - Duration.WHOLE.getLength())) {
      return n.getDuration() == Duration.HALF || n.getDuration() == Duration.WHOLE;
    }
    return n.getDuration() == Duration.HALF;
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
    return "Not a half note";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "In second species, all notes should be half notes except"
      + " for the last note and, optionally, the penultimate note";
  }
}
