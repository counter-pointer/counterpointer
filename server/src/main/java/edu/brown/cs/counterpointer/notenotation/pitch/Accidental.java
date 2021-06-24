package edu.brown.cs.counterpointer.notenotation.pitch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import edu.brown.cs.counterpointer.notenotation.exceptions.TextNotFoundException;

/**
 * Represents an accidental, which changes the note by 0, 1, or 2 half steps, up or down.
 */
public enum Accidental {
  DOUBLE_FLAT(-2, "bb"),
  FLAT(-1, "b"),
  NATURAL(0, ""),
  SHARP(1, "#"),
  DOUBLE_SHARP(2, "##");

  private final int valueChange;
  private final String abcjsText;

  /**
   * Enum constructor.
   * @param valueChange the number of half steps by which this accidental changes a note
   * @param abcjsText Text representation
   */
  Accidental(int valueChange, String abcjsText) {
    this.valueChange = valueChange;
    this.abcjsText = abcjsText;
  }

  /**
   * Getter for valueChange.
   * @return the number of half steps by which this accidental changes a note
   */
  @JsonValue
  public int getValueChange() {
    return this.valueChange;
  }

  @JsonCreator
  public static Accidental fromValueChange(int valueChange) {
    for (Accidental a : values()) {
      if (a.valueChange == valueChange) {
        return a;
      }
    }
    throw new IllegalArgumentException("Invalid valueChange " + valueChange);
  }

  /**
   * Getter for text.
   * @return the text for this accidental
   */
  public String getAbcjsText() {
    return abcjsText;
  }

  /**
   * Gets the accidental represented by the string.
   * @param s the string representation of a Accidental
   * @return the Accidental represented by s
   */
  public static Accidental byString(String s) {
    for (Accidental a : Accidental.values()) {
      if (a.getAbcjsText().equals(s)) {
        return a;
      }
    }
    throw new TextNotFoundException();
  }

  @Override
  public String toString() {
    return abcjsText;
  }
}
