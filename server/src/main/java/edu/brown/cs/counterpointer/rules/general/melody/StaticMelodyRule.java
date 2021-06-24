package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.rules.Rule;
import edu.brown.cs.counterpointer.rules.RuleUtilities;

import java.util.List;

/**
 * Makes sure the melody moves around rather than circling the same few notes.
 */
public class StaticMelodyRule implements Rule {
  public StaticMelodyRule() { }

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
    if (cm.getNotes().size() < 7) {
      // fewer than 7 notes in a narrow range is not boring
      return true;
    } else {
      List<Note> last7Notes
          = cm.getNotes().subList(cm.getNotes().size() - 7, cm.getNotes().size());
      Melody sevenNoteSubmelody = new Melody(last7Notes, cm.getKey(), cm.getUnitBeat());
      return (RuleUtilities.highestNote(sevenNoteSubmelody)
          .interval(RuleUtilities.lowestNote(sevenNoteSubmelody))).getDistance() > 2;
    }
  }

  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @Override
  public String name() {
    return "Melody must not hover around the same few notes";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "Don't confine yourself to using the same 3 pitches for too long; make the melody more interesting "
        + "by adding leaps or more motion in a certain direction.";
  }
}
