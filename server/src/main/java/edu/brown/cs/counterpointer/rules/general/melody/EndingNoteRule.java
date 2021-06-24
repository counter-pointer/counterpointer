package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.exceptions.BeatOutOfBoundsException;
import edu.brown.cs.counterpointer.notenotation.exceptions.PitchNotInScaleException;
import edu.brown.cs.counterpointer.notenotation.key.Key;
import edu.brown.cs.counterpointer.notenotation.key.ScaleDegree;
import edu.brown.cs.counterpointer.rules.Rule;

import java.util.ArrayList;
import java.util.List;

/**
 * A rule indicating that the last note of any countermelody should be the tonic.
 */
public class EndingNoteRule implements Rule {
  @Override
  public boolean passes(Melody m, Melody cm, Note n) {
    List<Note> newCmNotes = new ArrayList<>(cm.getNotes());
    newCmNotes.add(n);
    Melody newCm = new Melody(newCmNotes, cm.getKey(), cm.getUnitBeat());
    try {
      Note lastNoteM = m.at(m.length() - m.getUnitBeat().getLength());
      Note lastNote = newCm.at(m.length() - lastNoteM.getDuration().getLength());
      Key cmKey = newCm.getKey();
      return cmKey.getScaleDegree(lastNote).equals(ScaleDegree.TONIC);
      // the cm hasn't reached the end yet and doesn't need to be ont eh tonic
    } catch (BeatOutOfBoundsException b) {
      return true;
      // The last note wasn't even in the key
      // The last note was actually a rest
    } catch (PitchNotInScaleException | NullPointerException p) {
      return false;
    }
  }

  @Override
  public String name() {
    return "Countermelody does not end on tonic";
  }

  @Override
  public String description() {
    return "Countermelodies must end on the tonic, which is an octave or unison"
        + " from the end of a properly written countermelody";
  }
}
