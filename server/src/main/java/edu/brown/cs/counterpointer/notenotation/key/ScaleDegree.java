package edu.brown.cs.counterpointer.notenotation.key;

/**
 * Represents the scale degree of a note, which, numerically, is how far a given
 * note is away from the tonic, or first note of the scale, measured in scale
 * degrees (not semitones); in other words, the nth note in an ordered collection
 * of the seven unique pitches which comprise a key. The tonic is the "home-base"
 * of a given key, the note on which a listener wants a piece in that key to end.
 * It is also the beginning of the above-mentioned ordered collection (aka. scale).
 */
public enum ScaleDegree {
  TONIC(0),
  SUPERTONIC(1),
  MEDIANT(2),
  SUBDOMINANT(3),
  DOMINANT(4),
  SUBMEDIANT(5),
  LEADING_TONE(6);

  private int degree;

  /**
   * Enum constructor.
   * @param degree the number of above the tonic a scale degree is
   */
  ScaleDegree(int degree) {
    this.degree = degree;
  }

  /**
   * Getter for degree.
   * @return the number of above the tonic a scale degree is
   */
  public int getDegree() {
    return degree;
  }
}
