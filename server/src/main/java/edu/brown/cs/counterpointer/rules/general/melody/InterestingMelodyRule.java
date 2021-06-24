package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.rules.Rule;
import edu.brown.cs.counterpointer.rules.RuleUtilities;

import java.util.List;

/**
 * A rule giving a guideline for the number of times a melody should change direction.
 */
public class InterestingMelodyRule implements Rule {
  private int numChanges;
  private int perMeasure;
  public InterestingMelodyRule(int numChanges, int perMeasure) {
    this.numChanges = numChanges;
    this.perMeasure = perMeasure;
  }


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
    // The number of times the melody changes direction is over the limit per measure
    List<Note> notes = cm.getNotes();
    notes.add(n);
    Melody newCm = new Melody(notes, cm.getKey(), cm.getUnitBeat());
    Melody newCmSlice = newCm.subMelody(Math.max(0, newCm.length() - perMeasure * 4), newCm.length());
    int numChangesInCmSlice = RuleUtilities.directionChangeNotes(newCmSlice).size() - 1;
    if (newCmSlice.length() < perMeasure * 4) {
      int opportunitiesToMakeChanges = perMeasure * 4 - (int) newCmSlice.length();
      int numChangesLeftToMake = numChanges - numChangesInCmSlice;
      return opportunitiesToMakeChanges >= numChangesLeftToMake;
    }
    return numChangesInCmSlice >= numChanges;
  }


  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @Override
  public String name() {
    return "Melody insufficiently interesting";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "Interesting melodies should change direction somewhat frequently";
  }
}
