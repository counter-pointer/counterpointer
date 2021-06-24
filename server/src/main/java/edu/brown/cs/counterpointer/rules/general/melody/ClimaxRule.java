package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.notenotation.Duration;
import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.rules.Rule;
import edu.brown.cs.counterpointer.rules.RuleUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * A rule indicating that the highest note of a melody must only occur once.
 */
public class ClimaxRule implements Rule {
  @Override
  public boolean passes(Melody m, Melody cm, Note n) {
    List<Note> newCmNotes = new ArrayList<>(cm.getNotes());
    newCmNotes.add(n);
    Melody newCm = new Melody(newCmNotes, cm.getKey(), cm.getUnitBeat());
    // the climax occurs once
    if (RuleUtilities.climaxOccurrences(cm).size() > 1) {
      return false;
    } else {
      if (RuleUtilities.highestNote(newCm) == null) {
        return true;
      }
      return !newCm.getKey()
          .isLeadingTone(new Note(RuleUtilities.highestNote(newCm), Duration.WHOLE));
    }
  }

  @Override
  public String name() {
    return "Multiple climaxes, or climax at seventh";
  }

  @Override
  public String description() {
    return "The climax, or highest, note should only occur at one point"
        + " and should not be the leading tone";
  }
}
