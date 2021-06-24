package edu.brown.cs.counterpointer.analysis.generators;

import edu.brown.cs.counterpointer.analysis.Score;
import edu.brown.cs.counterpointer.analysis.checkers.Checker;
import edu.brown.cs.counterpointer.notenotation.Duration;
import edu.brown.cs.counterpointer.notenotation.Melody;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.pitch.Accidental;
import edu.brown.cs.counterpointer.notenotation.pitch.Pitch;
import edu.brown.cs.counterpointer.notenotation.pitch.ScaleValue;
import edu.brown.cs.counterpointer.rules.Rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Generator {

  private final List<Rule> rules;
  private static final List<Note> UPPER_NOTES = generateNotesBetweenOctaves(4, 6);
  private static final List<Note> LOWER_NOTES = generateNotesBetweenOctaves(2, 4);

  private static List<Note> generateNotesBetweenOctaves(int min, int max) {
    List<Note> notes = new ArrayList<>();

    // Creates rest notes
    List<Duration> allDurations = List.of(Duration.QUARTER, Duration.HALF, Duration.WHOLE);
    for (Duration duration : allDurations) {
      Note restNote = new Note(null, duration);
      notes.add(restNote);
    }

    for (Duration duration : allDurations) {
      for (ScaleValue sVal : ScaleValue.values()) {
        for (int octave = min; octave <= max; octave++) {
          for (Accidental accidental : Accidental.values()) {
            Pitch pitch = new Pitch(sVal, octave, accidental);
            Note note = new Note(pitch, duration);
            notes.add(note);
          }
        }
      }
    }
    return notes;
  }

  /**
   * Constructor for generator.
   *
   * @param c Checker
   */
  public Generator(Checker c) {
    rules = c.getRules();
  }

  /**
   * Generates a collection of next possible notes.
   *
   * @param s Score
   * @return Collection of possible notes that can be added onto the counter melody.
   */
  public List<Note> generatePossibleNext(Score s) {
    List<Note> suggestions = new ArrayList<>();
    List<Note> notes = getNotes(s);
    Melody m = s.getMelody();
    Melody cm = s.getCounterMelody();
    if (cm.length() > m.length()) {
      return Collections.emptyList();
    }

    for (Note note : notes) {
      boolean canAdd = true;

      for (Rule rule : rules) {
        if (s.getMelodyIsUpper()) {
          if (!rule.passesUpper(m, cm, note)) {
            canAdd = false;
            break;
          }
        } else {
          if (!rule.passesLower(m, cm, note)) {
            canAdd = false;
            break;
          }
        }
      }

      if (canAdd) {
        suggestions.add(note);
      }
    }
    return suggestions;
  }

  /**
   * Returns a list of lower notes if the melody in the score is upper.
   * Returns a list of upper notes if the melody in the score is lower.
   *
   * @param s Score
   * @return List of upper or lower notes.
   */
  public List<Note> getNotes(Score s) {
    if (s.getMelodyIsUpper()) {
      return LOWER_NOTES;
    } else {
      return UPPER_NOTES;
    }
  }

  /**
   * Generates a counter melody or auto-completes a semi-written counter melody.
   *
   * @param s Score
   * @return Generated counter melody
   */
  public Melody generateCounterMelody(Score s) {
    Melody m = s.getMelody();
    Melody cm = s.getCounterMelody();

    if (cm.length() >= m.length()) {
      return cm;
    }

    List<Note> possibleNotes = generatePossibleNext(s);
    Collections.shuffle(possibleNotes);

    for (Note note : possibleNotes) {
      Melody newCm = new Melody(cm.getNotes(), cm.getKey(), cm.getUnitBeat());
      newCm.append(note);
      Score newScore = new Score(m, newCm, s.getMelodyIsUpper());

      Melody generatedCm = generateCounterMelody(newScore);
      if (generatedCm != null) {
        return generatedCm;
      }
    }
    return null;
  }

}