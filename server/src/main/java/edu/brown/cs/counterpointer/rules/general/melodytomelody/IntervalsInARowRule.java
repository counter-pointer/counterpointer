package edu.brown.cs.counterpointer.rules.general.melodytomelody;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.interval.Interval;
import edu.brown.cs.counterpointer.rules.Rule;

import java.util.List;

/**
 * A Rule than can be extended that only allows a certain
 * number of certain intervals in a row.
 */
public class IntervalsInARowRule implements Rule {
  private int intervalsInARowLimit;
  private int[] intervalsRuleAppliedTo;
  private double offset;

  /**
   * Class constructor.
   * @param intervalsInARowLimit the maximum number of one of the intervals in
   *                             intervalsRuleAppliedTo that can be in a row
   * @param intervalsRuleAppliedTo the intervals the intervalsInARowLimit applies to
   */
  public IntervalsInARowRule(int intervalsInARowLimit,
                             int[] intervalsRuleAppliedTo,
                             double offset) {
    this.intervalsInARowLimit = intervalsInARowLimit;
    this.intervalsRuleAppliedTo = intervalsRuleAppliedTo;
    this.offset = offset;
  }

  @Override
  public boolean passes(Melody m, Melody cm, Note n) {
    int[] intervalCounts = new int[intervalsRuleAppliedTo.length];
    List<Note> cmNotes = cm.getNotes();
    cmNotes.add(n);
    Melody newCm = new Melody(cmNotes, cm.getKey(), cm.getUnitBeat());
    if (newCm.length() > m.length()) {
      return false;
    }

    Melody newM = m.subMelody(0, newCm.length());

    int notesChecked = 0;
    double currentBeat = Math.max(newM.length(), newCm.length());
    int nextMIndex = newM.getNotes().size() - 1;
    int nextCMIndex = newCm.getNotes().size() - 1;
    if (nextMIndex < 0 || nextCMIndex < 0) {
      return true;
    }

    double nextMBeat = newM.length() - newM.getNotes().get(nextMIndex).getDuration().getLength();
    double nextCMBeat = newCm.length() - newCm.getNotes().get(nextCMIndex).getDuration().getLength();

    while (notesChecked <= intervalsInARowLimit) {
      if (nextMBeat > nextCMBeat) {
        currentBeat = nextMBeat;
        nextMIndex--;
        if (nextMIndex >= 0) {
          nextMBeat -= newM.getNotes().get(nextMIndex).getDuration().getLength();
        }
      } else if (nextMBeat < nextCMBeat) {
        currentBeat = nextCMBeat;
        notesChecked++;
        nextCMIndex--;
        if (nextCMIndex >= 0) {
          nextCMBeat -= newCm.getNotes().get(nextCMIndex).getDuration().getLength();
        }
      } else {
        currentBeat = nextMBeat;
        notesChecked++;
        nextMIndex--;
        nextCMIndex--;
        if (nextMIndex >= 0 && nextCMIndex >= 0) {
          nextMBeat -= newM.getNotes().get(nextMIndex).getDuration().getLength();
          nextCMBeat -= newCm.getNotes().get(nextCMIndex).getDuration().getLength();
        }
      }
      Note mNote = newM.at(currentBeat);
      Note cmNote = newCm.at(currentBeat);

      Interval interval = mNote.interval(cmNote);
      if (interval == null) {
        if (nextMIndex < 0 && nextCMIndex < 0) {
          break;
        }
        continue;
      }
      for (int j = 0; j < intervalsRuleAppliedTo.length; j++) {
        if (intervalsRuleAppliedTo[j] == interval.getBaseInterval().getDistance()) {
          intervalCounts[j]++;
        } else {
          intervalCounts[j] = 0;
        }
      }
      if (nextMIndex < 0 && nextCMIndex < 0) {
        break;
      }
    }

    // check that none of the intervals in a row exceed intervalsInARowLimit
    for (int intervalCount : intervalCounts) {
      if (intervalCount > intervalsInARowLimit) {
        return false;
      }
    }
    return true;
  }

  /**
   * Builds a string containing all of the intervals the parallel interval rule is applied to.
   * @return the string
   */
  private String intervalList() {
    String pluralization;
    if (intervalsInARowLimit == 1) {
      pluralization = "";
    } else {
      pluralization = "s";
    }
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < intervalsRuleAppliedTo.length - 1; i++) {
      String suffix;
      if (intervalsRuleAppliedTo[i] == 0) {
        suffix = "st";
      } else if (intervalsRuleAppliedTo[i] == 1) {
        suffix = "nd";
      } else if (intervalsRuleAppliedTo[i] == 2) {
        suffix = "rd";
      } else {
        suffix = "th";
      }
      s.append(intervalsRuleAppliedTo[i] + 1)
          .append(suffix).append(pluralization).append(", ");
    }
    String lastString;
    if (intervalsRuleAppliedTo.length > 0) {
      String suffix;
      if (intervalsRuleAppliedTo[intervalsRuleAppliedTo.length - 1] == 0) {
        suffix = "st";
      } else if (intervalsRuleAppliedTo[intervalsRuleAppliedTo.length - 1] == 1) {
        suffix = "nd";
      } else if (intervalsRuleAppliedTo[intervalsRuleAppliedTo.length - 1] == 2) {
        suffix = "rd";
      } else {
        suffix = "th";
      }
      if (intervalsRuleAppliedTo.length == 1) {
        lastString = (intervalsRuleAppliedTo[0] + 1) + suffix + pluralization;
      } else {
        lastString = "or " + (intervalsRuleAppliedTo[intervalsRuleAppliedTo.length - 1] + 1)
            + suffix + pluralization;
      }
    } else {
      lastString = "";
    }
    s.append(lastString);
    return s.toString();
  }

  @Override
  public String name() {
    return "Parallel " + intervalList();
  }

  @Override
  public String description() {
    return "No more than " + intervalsInARowLimit + " " + intervalList() + " in a row";
  }
}
