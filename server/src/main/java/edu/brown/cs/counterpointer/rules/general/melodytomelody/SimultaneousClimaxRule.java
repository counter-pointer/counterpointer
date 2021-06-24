package edu.brown.cs.counterpointer.rules.general.melodytomelody;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.rules.Rule;
import edu.brown.cs.counterpointer.rules.RuleUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * RUle checking the climaxes of the two melodies are not on the same beat.
 */
public class SimultaneousClimaxRule implements Rule {
  @Override
  public boolean passes(Melody m, Melody cm, Note n) {
    List<Note> newCmNotes = new ArrayList<>(cm.getNotes());
    newCmNotes.add(n);
    Melody newCm = new Melody(newCmNotes, cm.getKey(), cm.getUnitBeat());
    for (double mBeat : RuleUtilities.climaxOccurrences(m)) {
      for (double cmBeat : RuleUtilities.climaxOccurrences(newCm)) {
        if (mBeat == cmBeat) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public String name() {
    return "Simultaneous climaxes";
  }

  @Override
  public String description() {
    return "The highest note of each melody must occur at a different place";
  }
}
