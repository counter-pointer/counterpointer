package edu.brown.cs.counterpointer.rules.species2;

/**
 * Interesting melody rule for species 1 and 2. (The melody should change direction 3 times
 * every 8 measures.)
 */
public class InterestingMelodyRule
  extends edu.brown.cs.counterpointer.rules.general.melody.InterestingMelodyRule {
  public InterestingMelodyRule() {
    super(3, 8);
  }
}
