package edu.brown.cs.counterpointer.notenotation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * A class representing rhythm (notes and the numbers of beats in them).
 */
public enum Duration {
  WHOLE(4), HALF(2), QUARTER(1), EIGHTH(0.5);

  private final double length;

  /**
   * Enum constructor.
   * @param length the number of beats in a note, assuming a quarter note is a beat
   */
  Duration(double length) {
    this.length = length;
  }

  /**
   * Getter for length.
   * @return the length of the note, in quarter-note beats
   */
  @JsonValue
  public double getLength() {
    return length;
  }

  @JsonCreator
  public static Duration fromLength(int length) {
    for (Duration d : values()) {
      if (d.length == length) {
        return d;
      }
    }
    throw new IllegalArgumentException("Invalid length " + length);
  }
}
