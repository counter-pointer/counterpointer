package edu.brown.cs.counterpointer.rules.species3;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.rules.Rule;
import edu.brown.cs.counterpointer.rules.RuleUtilities;

import java.util.List;

/**
 * Species 3 counterpoint is, aesthetically, known for its stepwise motion.
 * This rule enforces that.
 */
public class NotTooDisjunctRule implements Rule {
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
    int numLeaps = 3;
    // The number of times the melody changes direction is under the limit per measure
    List<Note> notes = cm.getNotes();
    notes.add(n);
    Melody newCm = new Melody(notes, cm.getKey(), cm.getUnitBeat());
    Melody newCmSlice = newCm.subMelody(Math.max(0, newCm.length() - 18), newCm.length());
    int numLeapsInSlice = RuleUtilities.numLeaps(newCmSlice);
    return numLeapsInSlice <= numLeaps;
  }

  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @Override
  public String name() {
    return "Don't leap too much in third species";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "Aesthetically, third species should be ";
  }
}
