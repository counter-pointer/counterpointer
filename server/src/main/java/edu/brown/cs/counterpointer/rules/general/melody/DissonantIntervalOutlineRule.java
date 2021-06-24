package edu.brown.cs.counterpointer.rules.general.melody;

import edu.brown.cs.counterpointer.notenotation.Duration;
import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.pitch.Pitch;
import edu.brown.cs.counterpointer.rules.Rule;
import edu.brown.cs.counterpointer.rules.RuleUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A rule indicating that the intervals between consecutive notes at which the melody
 * changes direction cannot be melodically dissonant.
 */
public class DissonantIntervalOutlineRule implements Rule {
  @Override
  public boolean passes(Melody m, Melody cm, Note n) {
    // Check if adding n makes the last note of cm into a direction change point.
    // If not, we don't have to worry about a violation from adding n.

    // ignore rests
    if (n.isRest()) {
      return true;
    }
    try {
      // filter repeats and rests
      List<Note> formattedMelody = RuleUtilities.stripRepeatsInARow(cm.getNotes())
          .stream().filter(note -> !note.isRest()).collect(Collectors.toList());

      Note penultimateCmNote = formattedMelody.get(formattedMelody.size() - 2);
      Note lastCmNote = formattedMelody.get(formattedMelody.size() - 1);
      Pitch penultimateCmPitch = penultimateCmNote.getPitch();
      Pitch lastCmPitch = lastCmNote.getPitch();
      Pitch nPitch = n.getPitch();
      if (!(n.getPitch().compareTo(lastCmPitch) != 0
        && lastCmPitch.compareTo(nPitch) == lastCmPitch.compareTo(penultimateCmPitch))) {
        return true;
      }
      Rule dissonantLeapRule = new DissonantLeapRule();

      List<Note> newNotes = new ArrayList<>();
      for (Pitch pitch : RuleUtilities.directionChangeNotes(cm)) {
        newNotes.add(new Note(pitch, Duration.WHOLE));
      }
      Melody newMelody = new Melody(newNotes, cm.getKey(), cm.getUnitBeat());
      return dissonantLeapRule.passes(m, newMelody, cm.getNotes().get(cm.getNotes().size() - 1));
    } catch (IndexOutOfBoundsException i) {
      // The melody was too short to outline any interval
      return true;
    }
  }

  @Override
  public String name() {
    return "Dissonant intervals outlined";
  }

  @Override
  public String description() {
    return "Two consecutive places at which the melody changes direction cannot "
        + "have a dissonant interval between them.";
  }
}
