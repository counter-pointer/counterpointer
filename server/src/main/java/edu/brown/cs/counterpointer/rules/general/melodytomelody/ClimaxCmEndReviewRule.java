package edu.brown.cs.counterpointer.rules.general.melodytomelody;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.rules.Rule;
import edu.brown.cs.counterpointer.rules.general.melody.ClimaxRule;

import java.util.List;

/**
 * At the end of a melody, checks to make sure none of the climax rules have been violated.
 */
public class ClimaxCmEndReviewRule implements Rule {
  private final static ClimaxRule c = new ClimaxRule();
  private final static SimultaneousClimaxRule s = new SimultaneousClimaxRule();

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
    List<Note> newMelodyNotes = cm.getNotes();
    newMelodyNotes.add(n);
    Melody newMelody = new Melody(newMelodyNotes, cm.getKey(), cm.getUnitBeat());
    if (newMelody.length() == m.length()) {
      return c.passes(m, newMelody, n) && s.passes(m, newMelody, n);
    }
    return true;
  }

  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @Override
  public String name() {
    return c.name() + " or " + s.name();
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return c.description() + ". " + s.description();
  }
}
