package edu.brown.cs.counterpointer.rules.species3;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.interval.Interval;
import edu.brown.cs.counterpointer.rules.Rule;

/**
 * Intervals greater than two octaves are not allowed in third species.
 */
public class SpacingRule implements Rule {

  @Override
  public boolean passes(Melody m, Melody cm, Note n) {
    if (m.length() == 0) {
      return true;
    }
    // no interval larger than a 15th is allowed (distance of 14)
    Interval nextInterval = n.interval(m.at(cm.length()));
    return nextInterval == null || (nextInterval.getDistance() < 15);
  }

  @Override
  public String name() {
    return "No harmonic interval greater than 2 octaves";
  }

  @Override
  public String description() {
    return "In third species, no harmonic interval can be larger than 2 octaves";
  }
}
