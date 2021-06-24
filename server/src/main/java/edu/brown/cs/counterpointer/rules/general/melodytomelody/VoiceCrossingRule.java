package edu.brown.cs.counterpointer.rules.general.melodytomelody;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.exceptions.BeatOutOfBoundsException;
import edu.brown.cs.counterpointer.rules.Rule;

/**
 * A Rule ensuring that melodic lines do not encroach on each other's local ranges.
 */
public class VoiceCrossingRule implements Rule {
  @Override
  public boolean passes(Melody m, Melody cm, Note n) {
    throw new java.lang.UnsupportedOperationException();
  }

  private boolean passes(Melody m, Melody cm, Note n, int comparisonInt) {
    // It can be assumed that the function will not be called on a countermelody longer
    // than the melody
    double[] beatsToCheck = new double[]{-1 * cm.getUnitBeat().getLength(),
        0,
        cm.getUnitBeat().getLength(),
        n.getDuration().getLength()};
    for (double beatBeingChecked : beatsToCheck) {
      try {
        if (m.at(cm.length() + beatBeingChecked).getPitch()
            .compareTo(n.getPitch()) == comparisonInt) {
          return false;
        }
        // The note in question is a rest or there is no note here
        // ignore (voice crossing doesn't matter here)
      } catch (NullPointerException | BeatOutOfBoundsException e) {
        continue;
      }
    }
    return true;
  }

  @Override
  public boolean passesUpper(Melody m, Melody cm, Note n) {
    // if the next note is lower than the melody notes during and before it
    return passes(m, cm, n, -1);
  }

  @Override
  public boolean passesLower(Melody m, Melody cm, Note n) {
    return passes(m, cm, n, 1);
  }

  @Override
  public String name() {
    return "Voice crossing/overlap";
  }

  @Override
  public String description() {
    return "The next note of the countermelody cannot exceed the current"
        + " or previous note of the melody; ie. a lower countermelody cannot"
        + " have a note higher than a current, directly previous, or directly next"
        + " note and vice versa.";
  }
}
