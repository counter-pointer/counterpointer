package edu.brown.cs.counterpointer.notenotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.brown.cs.counterpointer.notenotation.interval.Interval;
import edu.brown.cs.counterpointer.notenotation.pitch.Pitch;

/**
 * A class representing the two components of a musical note: its pitch and its length.
 */
public class Note {
  private final Pitch pitch;
  private final Duration duration;

  /**
   * Class constructor.
   * @param pitch the pitch of the note; how high or low it is
   * @param duration the length of a note
   */
  public Note(@JsonProperty("pitch") Pitch pitch,
              @JsonProperty("duration") Duration duration) {
    this.pitch = pitch;
    this.duration = duration;
  }

  /**
   * Getter for duration.
   * @return the duration of the note
   */
  public Duration getDuration() {
    return duration;
  }

  /**
   * Getter for pitch.
   * @return the pitch of the note
   */
  public Pitch getPitch() {
    return pitch;
  }

  /**
   * Returns whether this note is actually a rest, a moment of silence,
   * as designated by a null value for pitch.
   * @return a boolean indicating a Note's rest status
   */
  public boolean isRest() {
    return this.pitch == null;
  }

  /**
   * Returns the interval between two notes, or null if one of the notes is a rest.
   * @param other the note against this note will be compared
   * @return the edu.brown.cs.ading13_eholtz1_jchung63_obloomfi.NoteNotation.Interval
   * between the two notes
   */
  public Interval interval(Note other) {
    if (this.getPitch() != null && other.getPitch() != null) {
      return this.getPitch().interval(other.getPitch());
    } else {
      return null;
    }
  }

  @Override
  public String toString() {
    if (pitch == null) {
      return this.duration + " REST";
    } else {
      return this.duration + " NOTE: " + this.pitch;
    }
  }
}
