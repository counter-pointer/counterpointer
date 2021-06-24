package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.interval.Interval;
import edu.brown.cs.counterpointer.notenotation.interval.Intervals;
import edu.brown.cs.counterpointer.notenotation.key.Mode;
import edu.brown.cs.counterpointer.notenotation.key.ScaleDegree;
import edu.brown.cs.counterpointer.rules.Rule;

import java.util.List;

/**
 * A rule that ensures that the penultimate note is correct according
 * to species counterpoint guidelines.
 */
public class LeadingToneAccidentalRule implements Rule {

  @Override
  public boolean passes(Melody m, Melody cm, Note n) {
    List<Note> newMelodyNotes = cm.getNotes();
    newMelodyNotes.add(n);
    Melody newMelody = new Melody(newMelodyNotes, cm.getKey(), cm.getUnitBeat());
    // if all that is left to place is the final note, which is a whole note
    if (newMelody.length() ==
      m.length() - m.at(m.length() - m.getUnitBeat().getLength()).getDuration().getLength()) {
      // just let rests pass
      if (n.getPitch() == null) {
        return true;
      }
      // we can assume that the modes are the same
      Interval intervalWithTonic =
        cm.getKey().getPitch(ScaleDegree.TONIC, 0).interval(n.getPitch()).getBaseInterval();
      return cm.getKey().isLeadingTone(n) &&
      // if the mode is Phrygian, the note below it should be a whole step below the tonic
        ((cm.getKey().getMode().equals(Mode.PHRYGIAN)
          && (intervalWithTonic.equals(Intervals.MAJ2)
            || intervalWithTonic.equals(Intervals.MIN7)))
      // Otherwise, it should be a half step below the tonic
          || (!cm.getKey().getMode().equals(Mode.PHRYGIAN)
            && (intervalWithTonic.equals(Intervals.MIN2)
            || intervalWithTonic.equals(Intervals.MAJ7))));
    }
    return true;
  }

  @Override
  public String name() {
    return "Penultimate note must be the leading tone";
  }

  @Override
  public String description() {
    return "The penultimate note should be a half step below the tonic unless the mode "
        + "is phrygian, in which case it should be a whole step below the tonic.";
  }
}
