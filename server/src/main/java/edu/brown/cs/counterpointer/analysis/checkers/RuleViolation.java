package edu.brown.cs.counterpointer.analysis.checkers;

import edu.brown.cs.counterpointer.rules.Rule;

import java.util.Objects;

/**
 * Class representing a rule violation.
 */
public class RuleViolation {

  private final Rule rule;
  private final double beat;

  /**
   * Constructor for a rule violation.
   *
   * @param ruleIn Rule that was violated
   * @param beatIn Beat where the rule was violated
   */
  public RuleViolation(Rule ruleIn, double beatIn) {
    rule = ruleIn;
    beat = beatIn;
  }

  /**
   * Getter for rule.
   *
   * @return Rule that was violated
   */
  public Rule getRule() {
    return rule;
  }

  /**
   * Getter for beat.
   *
   * @return Beat where the rule was violated
   */
  public double getBeat() {
    return beat;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RuleViolation that = (RuleViolation) o;
    return Double.compare(that.beat, beat) == 0 && rule.name().equals(that.rule.name());
  }

  @Override
  public int hashCode() {
    return Objects.hash(rule.name(), beat);
  }
}
