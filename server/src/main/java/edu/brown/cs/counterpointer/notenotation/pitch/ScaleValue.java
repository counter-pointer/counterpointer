package edu.brown.cs.counterpointer.notenotation.pitch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import edu.brown.cs.counterpointer.notenotation.exceptions.TextNotFoundException;

/**
 * Represents a note in the diatonic scale, minus the accidentals.
 */
public enum ScaleValue {
  C(0, 0, "C"),
  D(2, 1, "D"),
  E(4, 2, "E"),
  F(5, 3, "F"),
  G(7, 4, "G"),
  A(9, 5, "A"),
  B(11, 6, "B");

  private final int value;
  private final int degree;
  private final String abcjsText;

  /**
   * Enum constructor.
   * @param value the number of half steps the note is above the highest C lower than it
   * @param degree the number of notes (by note name) above the highest C lower than it
   */
  ScaleValue(int value, int degree, String abcjsText) {
    this.value = value;
    this.degree = degree;
    this.abcjsText = abcjsText;
  }

  /**
   * Getter for the value of a note.
   * @return the number of half steps the note is above the highest C lower than it
   */
  public int getValue() {
    return this.value;
  }

  /**
   * Getter for the degree of a note, regardless of accidentals.
   * @return the number of notes (by note name) above the highest C lower than it
   */
  @JsonValue
  public int getDegree() {
    return degree;
  }

  @JsonCreator
  public static ScaleValue fromDegree(int degree) {
    for (ScaleValue sv : values()) {
      if (sv.degree == degree) {
        return sv;
      }
    }
    throw new IllegalArgumentException("Invalid degree " + degree);
  }

  /**
   * Getter for abcjsText.
   * @return the abcjsText for this ScaleValue
   */
  public String getAbcjsText() {
    return abcjsText;
  }

  /**
   * Gets the ScaleValue represented by the string.
   * @param s the string representation of a ScaleValue
   * @return the ScaleValue represented by s
   */
  public static ScaleValue byString(String s) {
    for (ScaleValue sv : ScaleValue.values()) {
      if (sv.getAbcjsText().equals(s)) {
        return sv;
      }
    }
    throw new TextNotFoundException();
  }
}
