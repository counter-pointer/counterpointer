package edu.brown.cs.counterpointer.analysis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.brown.cs.counterpointer.notenotation.Melody;

/**
 * Class representing a score.
 */
@JsonIgnoreProperties(value={"key"})
public class Score {
  private final Melody melody;
  private final Melody counterMelody;
  private final boolean melodyIsUpper;

  /**
   * Constructor for the score.
   *
   * @param melody        Melody
   * @param counterMelody Countermelody
   * @param melodyIsUpper Boolean value indicating whether the melody is upper
   */
  public Score(@JsonProperty("melody") Melody melody,
               @JsonProperty("counterMelody") Melody counterMelody,
               @JsonProperty("isMelodyHigher") boolean melodyIsUpper) {
    this.melody = melody;
    this.counterMelody = counterMelody;
    this.melodyIsUpper = melodyIsUpper;
  }

  /**
   * Getter for melody.
   *
   * @return Melody
   */
  public Melody getMelody() {
    return melody;
  }

  /**
   * Getter for counterMelody.
   *
   * @return Countermelody
   */
  public Melody getCounterMelody() {
    return counterMelody;
  }

  /**
   * Getter for isMelodyUpper boolean.
   *
   * @return Boolean value indicating whether the melody is upper
   */
  public boolean getMelodyIsUpper() {
    return melodyIsUpper;
  }

}
