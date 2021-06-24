package edu.brown.cs.counterpointer.rules;

import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.exceptions.BeatOutOfBoundsException;
import edu.brown.cs.counterpointer.notenotation.interval.Interval;
import edu.brown.cs.counterpointer.notenotation.interval.IntervalQuality;
import edu.brown.cs.counterpointer.notenotation.interval.Intervals;
import edu.brown.cs.counterpointer.notenotation.pitch.Pitch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A utility class for the rules.
 */
public class RuleUtilities {
  /**
   * Gets the highest note of a melody.
   * @param m the melody of which to find the highest note
   * @return the highest note of Melody m
   */
  public static Pitch highestNote(Melody m) {
    List<Pitch> pitches = m.getNotes().stream().map(Note::getPitch)
        .filter(Objects::nonNull).collect(Collectors.toList());
    if (pitches.size() == 0) {
      return null;
    }
    return Collections.max(pitches);
  }

  /**
   * Gets the lowest note of a melody.
   * @param m the melody of which to find the lowest note
   * @return the lowest note of Melody m
   */
  public static Pitch lowestNote(Melody m) {
    List<Pitch> pitches = m.getNotes().stream().map(Note::getPitch)
        .filter(Objects::nonNull).collect(Collectors.toList());
    if (pitches.size() == 0) {
      return null;
    }
    return Collections.min(pitches);
  }

  /**
   * All beats at which the climax occurs.
   * @param m the melody
   * @return an arraylist of the beats at which the climax occurs
   */
  public static List<Double> climaxOccurrences(Melody m) {
    List<Double> beatList = new ArrayList<>();
    int noteIndex = -1;
    for (double i = 0; i < m.length(); i += m.at(noteIndex).getDuration().getLength()) {
      if (m.at(i).getPitch() != null && m.at(i).getPitch().equals(highestNote(m))) {
        beatList.add(i);
      }
      noteIndex++;
    }
    return beatList;
  }

  /**
   * An interval whose melodic dissonance is to be determined.
   * @param i the interval in question
   * @return whether i is dissonant
   */
  public static boolean isMelodicallyDissonant(Interval i) {
    return i != null
        && !i.getIntervalQuality().equals(IntervalQuality.UNCLASSIFIED)
        && (i.getBaseInterval().equals(Intervals.MAJ7)
        || i.getBaseInterval().equals(Intervals.MIN7)
        || i.getIntervalQuality().equals(IntervalQuality.DIMINISHED)
        || i.getIntervalQuality().equals(IntervalQuality.AUGMENTED)
        // Minor and major 2nds are not melodically dissonant, but not necessarily every interval
        // with those as a base interval.
        || ((i.getBaseInterval().equals(Intervals.MIN2) || i.getBaseInterval().equals(Intervals.MAJ2))
            && !i.equals(i.getBaseInterval())));
  }

  /**
   * An interval whose harmonic dissonance is to be determined.
   * @param i the interval in question
   * @return whether i is dissonant
   */
  public static boolean isHarmonicallyDissonant(Interval i) {
    return i != null && (RuleUtilities.isMelodicallyDissonant(i)
        || i.getBaseInterval().equals(Intervals.PER4)
        || i.getBaseInterval().equals(Intervals.MAJ2)
        || i.getBaseInterval().equals(Intervals.MIN2));
  }

  /**
   * Returns list containing the places where the melody changes direction.
   * @param m the melody in question
   * @return the places where m changes direction
   */
  public static List<Pitch> directionChangeNotes(Melody m) {
    // filter rest notes and repeated notes
    List<Note> allNotes = stripRepeatsInARow(m.getNotes()).stream()
        .filter(note -> note.getPitch() != null).collect(Collectors.toList());
    if (allNotes.size() == 0) {
      return new ArrayList<>();
    }
    List<Pitch> changingPitches = new ArrayList<>();
    changingPitches.add(allNotes.get(0).getPitch());
    for (int i = 1; i < allNotes.size() - 1; i++) {
      Pitch curPitch = allNotes.get(i).getPitch();
      Pitch prevPitch = changingPitches.get(changingPitches.size() - 1);
      Pitch nextPitch = allNotes.get(i + 1).getPitch();
      if (curPitch.compareTo(prevPitch) != 0
          && curPitch.compareTo(prevPitch) == curPitch.compareTo(nextPitch)) {
        changingPitches.add(curPitch);
      }
    }
    // ignore starting note
    return changingPitches;
  }

  /**
   * Returns the input list of notes with all notes which
   * follow a note of the same pitch stripped from the list.
   *
   * @param notes list of notes
   * @return list of notes
   */
  public static List<Note> stripRepeatsInARow(List<Note> notes) {
    List<Note> notesCopy = new ArrayList<>(notes);
    for (int i = 0; i < notesCopy.size() - 1; i++) {
      Note currNote = notesCopy.get(i);
      Note nextNote = notesCopy.get(i + 1);
      if (!(currNote.isRest() || nextNote.isRest()) &&
          currNote.getPitch().equals(nextNote.getPitch())) {
        notesCopy.remove(i + 1);
        i--;
      }
    }
    return notesCopy;
  }

  /**
   * Gets the number of leaps in a melody
   * @return the number of leaps in the melody
   */
  public static int numLeaps(Melody m) {
    List<Note> mNotes = m.getNotes();
    int leapCount = 0;
    for (int i = 0; i < mNotes.size() - 1; i++) {
      Interval interval = mNotes.get(i).interval(mNotes.get(i + 1));
      if (interval != null && interval.getDistance() > 2) {
        leapCount++;
      }
    }
    return leapCount;
  }
}
