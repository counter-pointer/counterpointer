package edu.brown.cs.counterpointer.rules.species3;

/**
 * Interesting melody rule for species 3. (The melody should change direction 2 times
 * every 3 measures.)
 */
public class InterestingMelodyRule extends edu.brown.cs.counterpointer.rules.general.melody.InterestingMelodyRule {
  public InterestingMelodyRule() {
    super(2, 3);
  }
}
