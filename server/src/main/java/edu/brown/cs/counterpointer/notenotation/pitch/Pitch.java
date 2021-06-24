package edu.brown.cs.counterpointer.notenotation.pitch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.brown.cs.counterpointer.notenotation.exceptions.AccidentalOutOfBoundsException;
import edu.brown.cs.counterpointer.notenotation.interval.Interval;

/**
 * Encodes all of the ideas of a pitch: note name, accidental, and octave.
 */
public class Pitch implements Comparable<Pitch> {
  private final ScaleValue scaleValue;
  private final int octave;
  private final Accidental accidental;

  /**
   * Class constructor.
   * @param scaleValue the note name (A-G) of the pitch
   * @param octave the octave (how high it is) of the pitch
   * @param accidental the number of half steps a note is below its default
   *                   scale value note
   */
  @JsonCreator
  public Pitch(@JsonProperty("scaleValue") ScaleValue scaleValue,
               @JsonProperty("octave") int octave,
               @JsonProperty("accidental") Accidental accidental) {
    this.accidental = accidental;
    this.octave = octave;
    this.scaleValue = scaleValue;
  }

  /**
   * Getter for scaleValue.
   * @return the note name (A-G) of the pitch
   */
  public ScaleValue getScaleValue() {
    return this.scaleValue;
  }

  /**
   * Getter for octave.
   * @return how high the note is. The higher the octave number, the higher the note.
   */
  public int getOctave() {
    return this.octave;
  }

  /**
   * Getter for accidental.
   * @return the number of half steps a note is below its default
   *         scale value note
   */
  public Accidental getAccidental() {
    return this.accidental;
  }

  /**
   * Gets the pitch, but with the accidental incremented up 1 (ie. natural to sharp,
   * or double-flat to flat).
   * @return The new pitch obtained after sharpening
   */
  public Pitch sharp() {
    int newValueChange = this.accidental.getValueChange() + 1;
    for (Accidental a : Accidental.values()) {
      if (a.getValueChange() == newValueChange) {
        return new Pitch(this.scaleValue, this.octave, a);
      }
    }
    throw new AccidentalOutOfBoundsException();
  }

  /**
   * Gets the pitch, but with the accidental incremented up 1 (ie. natural to flat,
   * or double-sharp to sharp)
   * @return The new pitch obtained after flattening
   */
  public Pitch flat() {
    int newValueChange = this.accidental.getValueChange() - 1;
    for (Accidental a : Accidental.values()) {
      if (a.getValueChange() == newValueChange) {
        return new Pitch(this.scaleValue, this.octave, a);
      }
    }
    throw new AccidentalOutOfBoundsException();
  }

  /**
   * Turns a Pitch into a numerical representation of its pitch in the musical sense so that
   * all enharmonic equivalents in the same octave (notes with the same frequency but not
   * necessarily the same pitch-accidental combination) are represented with the same number.
   * @return the numerical representation of that pitch as specified above
   */
  public int value() {
    final int notesInOctave = 12;
    return this.scaleValue.getValue()
        + this.accidental.getValueChange()
        + this.octave * notesInOctave;
  }

  /**
   * Turns a Pitch into a numerical representation of its pitch in the musical sense so that
   * notes with the same letter name are represented with the same number. These will not
   * necessarily be enharmonic equivalents.
   * @return the numerical representation of that pitch as specified above
   */
  public int degreeValue() {
    final int notesInOctave = 7;
    return this.scaleValue.getDegree()
        + this.octave * notesInOctave;
  }

  /**
   * Returns whether two notes are enharmonically equivalent; ie. have the same frequency.
   * @param other another pitch, to be compared with this one
   * @return whether they are enharmonic equivalents
   */
  public boolean isEnharmonic(Pitch other) {
    return this.value() == other.value();
  }

  /**
   * Returns the interval between two pitches.
   * @param pitch a pitch to compare this interval to
   * @return the interval between this pitch and another
   */
  public Interval interval(Pitch pitch) {
    if (pitch == null) {
      return null;
    }
    return new Interval(
        // Notes above C0, by note name, excluding accidentals, subtracted from each other
        Math.abs(this.degreeValue() - pitch.degreeValue()),
        // Semitones above C0 subtracted from each other
        Math.abs(this.value() - pitch.value()));
  }

  @Override
  public int compareTo(Pitch p) {
    return Integer.compare(this.value(), p.value());
  }

  @Override
  public String toString() {
    return this.scaleValue + this.accidental.toString() + this.octave;
  }

  @Override
  public boolean equals(Object o) {
    try {
      Pitch other = (Pitch) o;
      return this.scaleValue.getValue() == other.getScaleValue().getValue()
          && this.octave == other.getOctave()
          && this.accidental.getValueChange() == other.getAccidental().getValueChange();
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public int hashCode() {
    /*
      This configuration guarantees that each unique combination of octave, pitch,
      and accidental have a unique number. It works sort of like a base system,
      with a base 'accidental', a base 'scaleValue', and a base 'octave'. The key
      difference between this system and an actual base system is that it is not
      intended to (nor does it make sense to) account for regrouping. It's simply
      a way of encoding the three components of a pitch numerically in such a way
      that it is impossible to obtain a given hashcode in two different ways.
      */
    final int scaleValueFactor = 10;
    final int octaveFactor = 100;
    return this.accidental.getValueChange() + 2
        + scaleValueFactor * (this.scaleValue.getValue() + 1)
        + octaveFactor * (this.octave + 1);
  }
}
