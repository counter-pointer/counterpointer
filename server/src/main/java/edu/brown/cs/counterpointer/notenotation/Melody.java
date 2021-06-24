package edu.brown.cs.counterpointer.notenotation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.brown.cs.counterpointer.notenotation.exceptions.BeatOutOfBoundsException;
import edu.brown.cs.counterpointer.notenotation.exceptions.EndBeatGreatThanBeginBeatException;
import edu.brown.cs.counterpointer.notenotation.key.Key;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * A class representing an ordered but *not necessarily sequential* series of notes
 * which form a tune, or Melody. This melody must consist of unique notes stored
 * at different memory locations, at the very least between consecutive notes.
 */
public class Melody {
  private final List<Note> notes;
  private final Key key;
  // The smallest rhythm that this melody will consider
  private final Duration unitBeat;
  private final Hashtable<Integer, Note> noteMap = new Hashtable<>();
  private final Hashtable<Integer, Integer> beatMap = new Hashtable<>();
  private int currentBeat = 0;

  /**
   * Class constructor.
   * @param notes The notes in the melody, as a List
   * @param key the key of the melody, or set of notes it uses
   * @param unitBeat the smallest rhythm the melody will encounter
   */
  public Melody(List<Note> notes, Key key, Duration unitBeat) {
    this.notes = notes;
    this.key = key;
    this.unitBeat = unitBeat;
    for (int i = 0; i < notes.size(); i++) {
      addNoteToMap(notes.get(i), i);
    }
  }

  @JsonCreator
  public Melody(@JsonProperty("notes") List<Note> notes,
                @JsonProperty("key") Key key) {
    this(notes, key, Duration.QUARTER);
  }

  private void addNoteToMap(Note n, int noteIndex) {
    double length = n.getDuration().getLength();
    int numBeats = (int) (length / unitBeat.getLength());

    // check that length is divisible by unit beat
    if (Math.abs(numBeats * unitBeat.getLength() - length) > Double.MIN_VALUE) {
      throw new IllegalArgumentException("Every note's duration must be divisible by the unit beat");
    }
    for (int i = 0; i < numBeats; i++) {
      noteMap.put(i + currentBeat, n);
      beatMap.put(i + currentBeat, noteIndex);
    }
    currentBeat += numBeats;
  }

  /**
   * Appends a note to the melody.
   * @param n the note to be added
   */
  public void append(Note n) {
    addNoteToMap(n, notes.size());
    notes.add(n);
  }

  private int beatAt(double beat) {
    return (int) (beat / unitBeat.getLength());
  }

  /**
   * Gets the note at the specified beat.
   * @param beat the beat (or half-beat, quarter-beat, etc) to find the note from
   * @return the note on beat
   */
  public Note at(double beat) {
    int numBeats = beatAt(beat);

    Note n = noteMap.get(numBeats);
    if (n == null) {
      throw new BeatOutOfBoundsException();
    }

    return n;
  }

  /**
   * Returns a submelody from the floor of the begin beat to
   * (exclusive of) the floor of the end beat.
   * @param begin the beat on which to begin the submelody
   * @param end the beat on which to end the submelody
   * @return the submelody between these two beats
   */
  public Melody subMelody(double begin, double end) {
    if (begin > end) {
      throw new EndBeatGreatThanBeginBeatException();
    }

    if (Double.compare(begin, end) == 0) {
      return new Melody(new ArrayList<>(), key, unitBeat);
    }

    if (begin < 0) {
      throw new BeatOutOfBoundsException();
    }

    if (end > length()) {
      throw new BeatOutOfBoundsException();
    }

    int startBeat = beatAt(begin);
    int endBeat = beatAt(end);
    if (unitBeat.getLength() * endBeat < end) {
      endBeat++;
    }

    if (!beatMap.containsKey(startBeat)
        || (endBeat != currentBeat && !beatMap.containsKey(endBeat))) {
      throw new BeatOutOfBoundsException("Beat does not map to a note");
    }

    int startIndex = beatMap.get(startBeat);
    int endIndex = endBeat == currentBeat ? notes.size() : beatMap.get(endBeat);

    if (endIndex > 0 && endBeat != currentBeat && (beatMap.get(endBeat).equals(beatMap.get(endBeat - 1)))) {
      endIndex++;
    }

    return new Melody(notes.subList(startIndex, endIndex), key, unitBeat);
  }

  /**
   * Returns the notes in a melody.
   * @return this.notes
   */
  public List<Note> getNotes() {
    return new ArrayList<>(notes);
  }

  /**
   * Returns the key, or set of notes, of the melody.
   * @return this melody's key
   */
  public Key getKey() {
    return key;
  }

  /**
   * Returns the smallest beat value.
   * @return this.smallestBeatValue
   */
  public Duration getUnitBeat() {
    return this.unitBeat;
  }

  /**
   * Return the length of the melody, in beats.
   * @return the value of the first beat/sub-beat on which there is no note
   */
  public double length() {
    return notes.stream()
        .map(note -> note.getDuration().getLength())
        .reduce(0.0, Double::sum);
  }
}
