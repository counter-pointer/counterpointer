package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.exceptions.BeatOutOfBoundsException;
import edu.brown.cs.counterpointer.notenotation.interval.Interval;
import edu.brown.cs.counterpointer.rules.Rule;
import edu.brown.cs.counterpointer.rules.RuleUtilities;

/**
 * A rule indicating that no leaps may be between two melodically dissonant notes.
 */
public class DissonantLeapRule implements Rule {
  @Override
  public boolean passes(Melody m, Melody cm, Note n) {
    try {
      Interval lastInterval = cm.at(cm.length() - cm.getUnitBeat().getLength()).interval(n);
      return !(RuleUtilities.isMelodicallyDissonant(lastInterval));
    } catch (BeatOutOfBoundsException b) {
      // if the length is 0, you can't break this rule because there is no
      // note to leap from
      return true;
    }
  }

  @Override
  public String name() {
    return "Dissonant melodic interval";
  }

  @Override
  public String description() {
    return "Dissonant melodic intervals, including all augmented and "
        + "diminished intervals, as well as the Major and minor 7ths, are not allowed.";
  }
}
