package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.interval.Interval;
import edu.brown.cs.counterpointer.rules.Rule;

/**
 * A rule ensuring there any interval between two notes is no greater than one octave.
 */
public class NoLeapsGreaterThanAnOctaveRule implements Rule {
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
    // need at least 1 note before the new note
    if (cm.getNotes().size() == 0) {
      return true;
    }
    // no leap greater than an octave
    Note lastNote = cm.getNotes().get(cm.getNotes().size() - 1);
    Interval interval = lastNote.interval(n);
    // if either note is a rest, it's okay
    if (interval == null) {
      return true;
    }
    return interval.getDistance() < 8;
  }

  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @Override
  public String name() {
    return "No leaps greater than an octave";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "The distance between two successive notes should not exceed an octave";
  }
}
