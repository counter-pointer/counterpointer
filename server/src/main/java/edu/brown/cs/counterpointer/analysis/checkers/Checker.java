package edu.brown.cs.counterpointer.analysis.checkers;

import edu.brown.cs.counterpointer.analysis.Score;
import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.rules.Rule;
import edu.brown.cs.counterpointer.rules.general.melodytomelody.CounterMelodyLengthRule;
import edu.brown.cs.counterpointer.rules.general.melodytomelody.MelodyLengthRule;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the checker.
 */
public class Checker {
  private final List<Rule> rules;

  public List<Rule> getRules() {
    return rules;
  }

  /**
   * Constructor for the checker.
   *
   * @param rulesIn List of rules
   */
  public Checker(List<Rule> rulesIn) {
    rules = rulesIn;
  }

  /**
   * Checks for violations for each rule in the countermelody.
   *
   * @param s Score
   * @return List of violated rules
   */
  public List<RuleViolation> check(Score s) {
    Melody m = s.getMelody();
    Melody cm = s.getCounterMelody();
    List<RuleViolation> violations = new ArrayList<>();

    // iterate through notes to check for violations
    double currentBeat = 0;
    while (currentBeat < cm.length()) {
      Note currNote = cm.at(currentBeat);
      Melody subCm = cm.subMelody(0, currentBeat);
      if (subCm.length() + currNote.getDuration().getLength() > m.length()) {
        violations.add(new RuleViolation(new MelodyLengthRule(), m.length()));
        break;
      }
      for (Rule rule : rules) {
        if (s.getMelodyIsUpper()) {
          if (!rule.passesUpper(m, subCm, currNote)) {
            RuleViolation newViolation = new RuleViolation(rule, currentBeat);
            violations.add(newViolation);
          }
        } else {
          if (!rule.passesLower(m, subCm, currNote)) {
            RuleViolation newViolation = new RuleViolation(rule, currentBeat);
            violations.add(newViolation);
          }
        }
      }
      currentBeat += currNote.getDuration().getLength();
    }

    // check to ensure that the countermelody is not too short
    if (cm.length() < m.length()) {
      violations.add(new RuleViolation(new CounterMelodyLengthRule(), cm.length()));
    }

    return violations;
  }
}
