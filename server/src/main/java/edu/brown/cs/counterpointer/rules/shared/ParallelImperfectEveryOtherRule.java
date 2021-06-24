package edu.brown.cs.counterpointer.rules.shared;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.rules.Rule;

import java.util.ArrayList;
import java.util.List;

/**
 * In species 2 & 3, parallel imperfect intervals still count if they
 * occur between alternating notes.
 */
public class ParallelImperfectEveryOtherRule implements Rule {
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
    if (cm.length() < 2 * n.getDuration().getLength() || m.length() == 0) {
      return true;
    }
    List<Note> intermediateNotes = cm.getNotes();
    intermediateNotes.add(n);
    Melody intermediateMelody = new Melody(intermediateNotes, cm.getKey(), cm.getUnitBeat());
    List<Note> newNotes = new ArrayList<>();
    List<Note> newMelodyNotes = new ArrayList<>();
    for (int i = 0;
         i < Math.min(intermediateMelody.length(), m.length());
         i += n.getDuration().getLength() * 2) {
      newNotes.add(intermediateMelody.at(i));
      newMelodyNotes.add(new Note(m.at(i).getPitch(),
        intermediateMelody.at(i).getDuration()));
    }
    Melody newM = new Melody(newMelodyNotes, cm.getKey(), cm.getUnitBeat());
    Melody newCm = new Melody(newNotes.subList(0, newMelodyNotes.size() - 1), m.getKey(), m.getUnitBeat());
    ParallelImperfectIntervalRule rule = new ParallelImperfectIntervalRule();
    return rule.passes(newM, newCm, newNotes.get(newNotes.size() - 1));
  }

  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @Override
  public String name() {
    return "Parallel imperfect intervals at the beginning of each measure exceed the allowed amount";
  }

  /**
   * Returns a description of the rule.
   *
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "Consecutive first beats of measures should not contain more than 3 parallel imperfect intervals;"
      + " if one measure begins on an imperfect interval, the next measure should not begin on"
      + " that same imperfect interval";
  }
}
