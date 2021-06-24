package edu.brown.cs.counterpointer.rules.general.melodytomelody;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.interval.Interval;
import edu.brown.cs.counterpointer.rules.Rule;

import edu.brown.cs.counterpointer.notenotation.interval.Intervals;

/**
 * A rule indicating that unisons are not allowed in the middle of a counterpoint.
 */
public class UnisonRule implements Rule {

  @Override
  public boolean passes(Melody m, Melody cm, Note n) {
    Interval lastInterval = m.at(cm.length()).interval(n);
    return (lastInterval == null || !(!(cm.length() == 0)
        // The countermelody is not about to append its last note
        && cm.length() < m.length() - n.getDuration().getLength()
        && lastInterval.equals(Intervals.PER1)));
  }

  @Override
  public boolean passesUpper(Melody m, Melody cm, Note n) {
    return passes(m, cm, n);
  }

  @Override
  public boolean passesLower(Melody m, Melody cm, Note n) {
    return passes(m, cm, n);
  }

  @Override
  public String name() {
    return "Unison in the middle of the counterpoint";
  }

  @Override
  public String description() {
    return "The only places at which unisons are permitted"
        + "are the beginning and end of a counterpoint.";
  }
}
