package edu.brown.cs.counterpointer.notenotation.key;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import edu.brown.cs.counterpointer.notenotation.exceptions.TextNotFoundException;

/**
 * Each of the modes, or types of scale. The number represents an offset, ie.
 * an offset of 2 means that given an Ionian scale (an ordered collection of 7
 * notes such that the pattern of differences in semitones between two notes is
 * 2-2-1-2-2-2), the scale with offset 2 begins on the note two notes after the
 * first note of the ionian scale, and appends the two "leftover" notes below
 * the first note of the new scale to the end of the new scale (albeit an octave
 * higher than they were).
 */
public enum Mode {
  IONIAN(0, "maj"),
  DORIAN(1, "dor"),
  PHRYGIAN(2, "phr"),
  MIXOLYDIAN(4, "mix"),
  AEOLIAN(5, "min");

  private final int offset;
  private final String abcjsText;

  /**
   * Calls constructor.
   * @param offset the number of scale degrees that this scale's
   *               tonic is from the tonic of the Ionian scale of the
   *               same key signature (set of notes).
   */
  Mode(int offset, String abcjsText) {
    this.offset = offset;
    this.abcjsText = abcjsText;
  }

  /**
   * Getter for offset.
   * @return the number of scale degrees the tonic of this key is offset from
   * the tonic of a major key fo the
   */
  @JsonValue
  public int getOffset() {
    return this.offset;
  }

  @JsonCreator
  public static Mode fromOffset(int offset) {
    for (Mode m : values()) {
      if (m.offset == offset) {
        return m;
      }
    }
    throw new IllegalArgumentException("Invalid offset " + offset);
  }

  /**
   * Getter for abcjsText.
   * @return the abcjs representation of the mode
   */
  public String getAbcjsText() {
    return abcjsText;
  }

  /**
   * Gets the Mode represented by the string.
   * @param s the string representation of a Mode
   * @return the Mode represented by s
   * @throws TextNotFoundException if the abcjs text is invalid
   */
  public static Mode getByString(String s) {
    for (Mode mode : Mode.values()) {
      if (mode.getAbcjsText().equals(s)) {
        return mode;
      }
    }
    throw new TextNotFoundException();
  }


  @Override
  public String toString() {
    return abcjsText;
  }
}
