package edu.brown.cs.counterpointer.rules.species1;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.exceptions.BeatOutOfBoundsException;
import edu.brown.cs.counterpointer.notenotation.interval.Interval;
import edu.brown.cs.counterpointer.notenotation.pitch.Pitch;
import edu.brown.cs.counterpointer.rules.Rule;
import edu.brown.cs.counterpointer.rules.RuleUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * A Rule ensuring that all leaps (notes which are more than 2 steps away from the previous
 * note) resolve (have a note following them that steps in the opposite direction of the leap).
 */
public class LeapsResolveRule implements Rule {
  private static final int MIN_LEAP_SIZE = 3;
  @Override
  public boolean passes(Melody m, Melody cm, Note n) {
    List<Note> cmNotes = cm.getNotes();
    cmNotes.add(n);
    cmNotes = RuleUtilities.stripRepeatsInARow(cmNotes);
    // need at least 3 notes in cm to check
    if (cmNotes.size() < 3) {
      return true;
    }
    Note firstNote = cmNotes.get(cmNotes.size() - 3);
    Note secondNote = cmNotes.get(cmNotes.size() - 2);

    Interval potentialLeap = firstNote.interval(secondNote);
    Interval potentialResolve = secondNote.interval(n);
    // just let pass if one note is a rest
    if (potentialLeap == null || potentialResolve == null) {
      return true;
    }
    // check if a leap has happened
    if (potentialLeap.getDistance() < MIN_LEAP_SIZE) {
      return true;
    }

    if (firstNote.getPitch().compareTo(secondNote.getPitch()) < 0) {
      // leap is upward

      // check that cm resolves down by step
      return (secondNote.getPitch().compareTo(n.getPitch()) > 0)
          && potentialResolve.getDistance() == 1;
    } else {
      // leap is downward

      // check that cm resolves up by step
      return (secondNote.getPitch().compareTo(n.getPitch()) < 0)
          && potentialResolve.getDistance() == 1;
    }
  }

  @Override
  public String name() {
    return "Leaps must resolve down by step";
  }

  @Override
  public String description() {
    return "After a leap (a melodic interval of a 4th or more), the"
        + " melody must resolve down by step. No sequential leaps allowed.";
  }
}
