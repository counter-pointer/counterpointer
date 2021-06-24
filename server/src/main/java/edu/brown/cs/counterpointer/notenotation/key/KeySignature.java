package edu.brown.cs.counterpointer.notenotation.key;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.brown.cs.counterpointer.notenotation.pitch.Accidental;
import edu.brown.cs.counterpointer.notenotation.pitch.ScaleValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Represents which accidentals should be applied to which pitches in a scale,
 * which is an ordered collection of pitches.
 */
public class KeySignature {
  private final static int NOTES_IN_A_SCALE = 7;

  private final List<Accidental> accidentals;
  private final ScaleValue ionianTonic;

  /**
   * Class constructor.
   * @param accidentals the accidentals to be applied to the pitches
   * @param ionianTonic the ScaleValue at which the scale starts, assuming
   *                    Ionian mode (an ordered collection of 7 notes such
   *                    that the pattern of differences in semitones between
   *                    two notes is 2-2-1-2-2-2)
   */
  @JsonCreator
  public KeySignature(@JsonProperty("accidentals") List<Accidental> accidentals,
                      @JsonProperty("ionianTonic") ScaleValue ionianTonic) {
    this.accidentals = accidentals;
    this.ionianTonic = ionianTonic;
  }

  /**
   * Returns what the tonic of the scale in this key signature would be if it were
   * Ionian.
   * @return the ionianTonic of this KeySignature
   */
  public ScaleValue getIonianTonic() {
    return ionianTonic;
  }

  /**
   * Gets the list of accidentals to be applied to the pitches at the corresponding
   * indices in the scale.
   * @return the accidentals of this key signature
   */
  public List<Accidental> getAccidentals() {
    return new ArrayList<>(accidentals);
  }

  /**
   * Gets the letter names in sequential order of the scale of a key beginning on
   * a specified note (ScaleValue).
   * @param tonic the ScaleValue the output scale should start on
   * @return a list containing the note names (as ScaleValues) of this key's scale
   */
  public List<ScaleValue> getScaleLetterNames(ScaleValue tonic) {
    // split the scale up into two fragments, the one tonic and above, and the
    // one below tonic
    List<ScaleValue> firstSegment =
        Arrays.asList(Arrays.copyOfRange(ScaleValue.values(),
            tonic.getDegree(),
            NOTES_IN_A_SCALE));
    List<ScaleValue> lastSegment =
        Arrays.asList(Arrays.copyOfRange(ScaleValue.values(),
            0,
            tonic.getDegree()));
    // put those fragments together in reversed order
    List<ScaleValue> scale = new ArrayList<>();
    scale.addAll(firstSegment);
    scale.addAll(lastSegment);
    return scale;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KeySignature that = (KeySignature) o;
    return accidentals.equals(that.accidentals) && ionianTonic == that.ionianTonic;
  }

  @Override
  public int hashCode() {
    return Objects.hash(accidentals, ionianTonic);
  }
}
