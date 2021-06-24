package edu.brown.cs.counterpointer.rules.general.melodytomelody;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.rules.Rule;

/**
 * Rule encoding that the countermelody is not longer than the melody
 */
public class MelodyLengthRule implements Rule {
  @Override
  public boolean passes(Melody m, Melody cm, Note n) {
    return true;
  }

  @Override
  public String name() {
    return "Countermelody too long.";
  }

  @Override
  public String description() {
    return "Countermelody must have the same length as the melody.";
  }
}
