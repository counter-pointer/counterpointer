package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.interval.Interval;
import edu.brown.cs.counterpointer.notenotation.interval.Intervals;
import edu.brown.cs.counterpointer.notenotation.key.Mode;
import edu.brown.cs.counterpointer.notenotation.key.ScaleDegree;
import edu.brown.cs.counterpointer.notenotation.pitch.Pitch;
import edu.brown.cs.counterpointer.rules.Rule;

import java.util.List;

/**
 * Checks that a note beign added to a melody is in the correct key.
 */
public class ValidKeyRule implements Rule {
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
    if (m.getNotes().size() < 1) {
      return true;
    }
    boolean atPenultimateNote = cm.length() + n.getDuration().getLength() ==
        m.length() - m.at(m.length() - m.getUnitBeat().getLength()).getDuration().getLength();
    List<Note> newMelodyNotes = cm.getNotes();
    newMelodyNotes.add(n);
    Melody newMelody = new Melody(newMelodyNotes, cm.getKey(), cm.getUnitBeat());
    boolean atNoteBeforePenultimate;
    if (m.getNotes().size() < 2) {
      atNoteBeforePenultimate = true;
    } else {
      double lastNoteBeats = m.at(m.length() - m.getUnitBeat().getLength()).getDuration().getLength();
      double penultimateNoteBeats =
          m.at(m.length() - lastNoteBeats - m.getUnitBeat().getLength()).getDuration().getLength();
      atNoteBeforePenultimate =
          m.length() - lastNoteBeats - penultimateNoteBeats == newMelody.length();
    }
    if (n.isRest()) {
      return true;
    }
    if (atPenultimateNote) {
      LeadingToneAccidentalRule forIfAtPenultimateNote = new LeadingToneAccidentalRule();
      return forIfAtPenultimateNote.passes(m, cm, n);
    } else if (cm.getKey().getMode().equals(Mode.AEOLIAN)) {
      if (atNoteBeforePenultimate) {
        Pitch tonicAtOctave0 = cm.getKey().getPitch(ScaleDegree.TONIC, 0);
        Pitch nAtOctave1 =
            new Pitch(n.getPitch().getScaleValue(), 1, n.getPitch().getAccidental());
        Interval toTonic = nAtOctave1.interval(tonicAtOctave0);
        // just let rests pass
        if (toTonic == null) {
          return true;
        }
        toTonic = toTonic.getBaseInterval();
        if (toTonic.equals(Intervals.MIN6)) {
          return false;
        } else if (toTonic.equals(Intervals.MAJ6)) {
          return true;
        } else {
          return cm.getKey().isInKey(n.getPitch());
        }
      }
    }
    return n.isRest() || cm.getKey().isInKey(n.getPitch());
  }

  /**
   * Returns the name of the rule.
   *
   * @return Name of the rule
   */
  @Override
  public String name() {
    return "Note not in key.";
  }

  /**
   * Returns a description of the rule.\
   * @return Description of the rule
   */
  @Override
  public String description() {
    return "All of the notes should be within the key of the countermelody"
      + " except for a leading tone, or potentially a 6th for Aeolian mode.";
  }
}
