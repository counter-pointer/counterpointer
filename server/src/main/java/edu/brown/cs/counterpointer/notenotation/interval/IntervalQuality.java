package edu.brown.cs.counterpointer.notenotation.interval;

/**
 * Represents the interval quality, which is the "type" of a given interval. What
 * distinguishes these "types" is the number of half steps between the two pitches.
 * For non-perfect intervals ([interval number] mod 8 ≠ 1, 4, or 5)
 * (Note that interval numbers do not correspond to the number of half steps between
 * two notes but rather the number of scale degrees + 1.)
 */
public enum IntervalQuality {
  DIMINISHED("diminished"),
  MINOR("minor"),
  PERFECT("Perfect"),
  MAJOR("Major"),
  AUGMENTED("Augmented"),
  UNCLASSIFIED("Unclassified");

  private String s;

  /**
   * Enum constructor.
   * @param s the string which represents the IntervalQuality.
   */
  IntervalQuality(String s) {
    this.s = s;
  }

  @Override
  public String toString() {
    return this.s;
  }
}
