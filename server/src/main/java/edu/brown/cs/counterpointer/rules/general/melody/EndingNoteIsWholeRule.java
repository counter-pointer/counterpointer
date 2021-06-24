package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.exceptions.BeatOutOfBoundsException;
import edu.brown.cs.counterpointer.notenotation.exceptions.PitchNotInScaleException;
import edu.brown.cs.counterpointer.notenotation.key.Key;
import edu.brown.cs.counterpointer.rules.Rule;
import java.util.ArrayList;
import java.util.List;

/**
 * A rule indicating that the last note should be a whole note.
 */
public class EndingNoteIsWholeRule implements Rule {
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
    // about to append the final note: is it a whole
    // turn it into a try statement, try to get the index (length minus the below), if ya can't true
    // you can get cm's cur len + 4
    double lengthOfLastMelodyNote =
        m.at(m.length() - m.getUnitBeat().getLength()).getDuration().getLength();
    List<Note> newCmNotes = new ArrayList<>(cm.getNotes());
    newCmNotes.add(n);
    Melody newCm = new Melody(newCmNotes, cm.getKey(), cm.getUnitBeat());
    try {
      Note lastNoteM = m.at(m.length() - m.getUnitBeat().getLength());
      double whereLastMelodyNoteStarts = m.length() - lastNoteM.getDuration().getLength();
      // this will throw an error if the last note has not yet been reached
      newCm.at(whereLastMelodyNoteStarts);
      Key cmKey = newCm.getKey();
      try {
        Note noteOnLastBeatOfCm = newCm.at(whereLastMelodyNoteStarts + 3);
        return !noteOnLastBeatOfCm.isRest();
        // the whole note didn't extend until the last beat
      } catch (BeatOutOfBoundsException b) {
        return false;
      }
      // the cm hasn't reached the end yet and doesn't need to be a whole note
    } catch (BeatOutOfBoundsException b) {
      return true;
      // The last note wasn't even in the key
      // The last note was actually a rest
    } catch (PitchNotInScaleException | NullPointerException p) {
      return false;
    }
  }

  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @Override
  public String name() {
    return "Last note must be a whole note";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "The last note in species counterpoint must be a whole note";
  }
}
