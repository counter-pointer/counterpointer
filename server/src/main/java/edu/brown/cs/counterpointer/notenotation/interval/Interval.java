package edu.brown.cs.counterpointer.notenotation.interval;

import edu.brown.cs.counterpointer.notenotation.exceptions.AccidentalOutOfBoundsException;
import edu.brown.cs.counterpointer.notenotation.exceptions.NegativeDistanceException;

import java.util.Objects;

/**
 * Represents an interval, or the distance between two notes.
 */
public class Interval {
  private int distance;
  private int semitoneDistance;
  private final int semitonesInOctave = 12;
  private final int degreesInOctave = 7;

  /**
   * Class constructor.
   * @param distance the distance between two notes by note names rather than semitones
   *                 ie. A to C is 2, and C to F is 3
   * @param semitoneDistance the distance between two notes by semitones
   */
  public Interval(int distance, int semitoneDistance) {
    if (distance < 0 || semitoneDistance < 0) {
      throw new NegativeDistanceException();
    }
    this.distance = distance;
    this.semitoneDistance = semitoneDistance;
  }

  /**
   * Getter for distance.
   * @return the distance between two notes by note names rather than semitones
   */
  public int getDistance() {
    return distance;
  }

  /**
   * Getter for semitone distance.
   * @return the distance between two notes by note names rather than semitones
   */
  public int getSemitoneDistance() {
    return semitoneDistance;
  }

  /**
   * Getter for distance.
   * @return the distance between two notes by semitones
   */
  private int getDefaultSemitones() {
    switch (distance) {
      case 0:
        return 0;
      case 1:
        return 2;
      case 2:
        return 4;
      case 3:
        return 5;
      case 4:
        final int semitonesInFifth = 7;
        return semitonesInFifth;
      case 5:
        final int semitonesInSixth = 9;
        return semitonesInSixth;
      case 6:
        final int semitonesInSeventh = 11;
        return semitonesInSeventh;
      case 7:
        return semitonesInOctave;
      default:
        return ((semitoneDistance / semitonesInOctave) * semitonesInOctave)
            + getBaseInterval().getDefaultSemitones();
    }
  }

  /**
   * Gets the base interval obtained when as many octaves are taken from between
   * two notes as possible while preserving the quality of the interval.
   * @return the base interval.
   */
  public Interval getBaseInterval() {
    int numOctaves = distance / degreesInOctave;
    if (semitoneDistance - semitonesInOctave * numOctaves >= 0) {
      return new Interval(distance % degreesInOctave,
          semitoneDistance - semitonesInOctave * numOctaves);
    } else {
      numOctaves = semitoneDistance / semitonesInOctave;
      // accounting for the diminished octave case
      return new Interval(distance - numOctaves * degreesInOctave,
          semitoneDistance - numOctaves * semitonesInOctave);
    }
  }

  /**
   * Gets the quality of the Interval.
   * @return the quality of the Interval
   */
  public IntervalQuality getIntervalQuality() {
    if (semitoneDistance >= 0 && distance >= 0) {
      // If this interval can be diminished, perfect, or augmented
      if (this.getBaseInterval().getDistance() == Intervals.PER1.getDistance()
          || this.getBaseInterval().getDistance() == Intervals.PER4.getDistance()
          || this.getBaseInterval().getDistance() == Intervals.PER5.getDistance()
          || this.getBaseInterval().getDistance() == Intervals.DIM8.getDistance()) {
        switch (this.getBaseInterval().getSemitoneDistance()
            - this.getBaseInterval().getDefaultSemitones()) {
          case -1:
            return IntervalQuality.DIMINISHED;
          case 0:
            return IntervalQuality.PERFECT;
          case 1:
            return IntervalQuality.AUGMENTED;
          default:
            return IntervalQuality.UNCLASSIFIED;
        }
      } else { // if it can be diminished, minor, major, or augmented
        final int diminishedOffset = -2;
        switch (this.getBaseInterval().getSemitoneDistance()
            - this.getBaseInterval().getDefaultSemitones()) {
          case diminishedOffset:
            return IntervalQuality.DIMINISHED;
          case -1:
            return IntervalQuality.MINOR;
          case 0:
            return IntervalQuality.MAJOR;
          case 1:
            return IntervalQuality.AUGMENTED;
          default:
            return IntervalQuality.UNCLASSIFIED;
        }
      }
    }
    return IntervalQuality.UNCLASSIFIED;
  }

  @Override
  public String toString() {
    IntervalQuality quality = getIntervalQuality();
    if (quality.equals(IntervalQuality.UNCLASSIFIED)) {
      return "Invalid interval";
    }
    return quality.toString() + " " + (getDistance() + 1);
  }

  @Override
  public boolean equals(Object o) {
    try {
      Interval other = (Interval) o;
      return other.getDistance() == this.getDistance()
          && other.getSemitoneDistance() == this.getSemitoneDistance();
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public int hashCode() {
    // IntelliJ-generated
    return Objects.hash(distance, semitoneDistance);
  }
}
