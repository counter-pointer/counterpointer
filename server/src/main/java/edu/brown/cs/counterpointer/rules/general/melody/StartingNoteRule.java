package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.exceptions.PitchNotInScaleException;
import edu.brown.cs.counterpointer.notenotation.key.ScaleDegree;
import edu.brown.cs.counterpointer.rules.Rule;

/**
 * Rule governing which pitches a melody may start on.
 */
public class StartingNoteRule implements Rule {
  @Override
  public boolean passes(Melody m, Melody cm, Note n) {
    if (cm.length() == 0) {
      try {
        // Either the note is the tonic or it started later (or both, although that's
        // not what this rule checks for)
        return cm.getKey().getScaleDegree(n.getPitch()).equals(ScaleDegree.TONIC);
      } catch (PitchNotInScaleException p) {
        // If it's not even in the scale, it's surely not the tonic
        return false;
      } catch (NullPointerException npe) {
        // it's a rest
        return true;
      }
    } else {
      return true;
    }
  }

  @Override
  public boolean passesLower(Melody m, Melody cm, Note n) {
    return passes(m, cm, n)
        || cm.getKey().getScaleDegree(n.getPitch()).equals(ScaleDegree.DOMINANT);
  }

  @Override
  public String name() {
    return "Melodies do not start an octave or a unison (or fifth, for an upper countermelody) apart.";
  }

  @Override
  public String description() {
    return "The countermelody must start on the tonic; the dominant is"
        + " also permissible for an upper countermelody.";
  }
}
