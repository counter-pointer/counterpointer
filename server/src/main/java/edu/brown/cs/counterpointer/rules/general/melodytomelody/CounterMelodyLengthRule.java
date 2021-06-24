package edu.brown.cs.counterpointer.rules.general.melodytomelody;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.rules.Rule;

/**
 * The countermelody is shorter than the melody.
 */
public class CounterMelodyLengthRule implements Rule {
  @Override
  public boolean passes(Melody m, Melody cm, Note n) {
    // always passes
    return true;
  }

  @Override
  public String name() {
    return "Countermelody too short";
  }

  @Override
  public String description() {
    return "Countermelody must have the same length as the melody";
  }
}