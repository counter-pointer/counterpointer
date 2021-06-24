package edu.brown.cs.counterpointer.notenotation.key;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.brown.cs.counterpointer.notenotation.Note;
import edu.brown.cs.counterpointer.notenotation.exceptions.InvalidScaleException;
import edu.brown.cs.counterpointer.notenotation.exceptions.PitchNotInScaleException;
import edu.brown.cs.counterpointer.notenotation.exceptions.TextNotFoundException;
import edu.brown.cs.counterpointer.notenotation.pitch.Accidental;
import edu.brown.cs.counterpointer.notenotation.pitch.Pitch;
import edu.brown.cs.counterpointer.notenotation.pitch.ScaleValue;
import java.util.List;

/**
 * A class representing a particular mode with a particular key signature. In
 * essence, this represents a repeating sequential pattern of 7 notes and a note
 * on which this pattern begins.
 */
public class Key {
  private final KeySignature keySignature;
  private final Mode mode;
  private final List<ScaleValue> scale;

  private final int notesInAScale = 7;

  /**
   * Class constructor.
   * @param keySignature a specification of which notes in a seven-note scale have
   *                     which accidentals applied ot them
   * @param mode the note on which a scale consisting of these notes begins
   */
  @JsonCreator
  public Key(@JsonProperty("keySignature") KeySignature keySignature,
             @JsonProperty("mode") Mode mode) {
    this.keySignature = keySignature;
    this.mode = mode;
    // Finds the the actual tonic of the keyAccidentalOutOfBoundsException
    ScaleValue tonic = ScaleValue.values()[(this.keySignature.getIonianTonic().getDegree()
        + this.mode.getOffset()) % notesInAScale];
    this.scale = this.keySignature.getScaleLetterNames(tonic);
  }

  /**
   * Class constructor.
   * @param abcjsString an abcjs string representing the key
   */
  public Key(String abcjsString) {
    if (abcjsString.length() < 4 || abcjsString.length() > 5) {
      throw new TextNotFoundException();
    }
    // the string representing the tonic
    String t = abcjsString.substring(0, abcjsString.length() - 3);
    String tLetter = t.substring(0, 1);
    String tAccidental = t.substring(1);
    ScaleValue tonic = ScaleValue.byString(tLetter);
    Accidental accidental = Accidental.byString(tAccidental);
    // the string representing the mode
    String m = abcjsString.toLowerCase().substring(abcjsString.length() - 3);
    Mode modeToBeSet = Mode.getByString(m);
    // Get the first accidental in a common practice period key signature which has
    // tonic at the appropriate index, as determined by the mode.
    // Set appropriate variables after having parsed the string
    this.keySignature = KeySignatures.getByNoteAtDegree(tonic,
        accidental,
        modeToBeSet.getOffset()
    );
    this.mode = modeToBeSet;
    this.scale = this.keySignature.getScaleLetterNames(tonic);
  }

  /**
   * Returns the KeySignature of this key.
   * @return this.keySignature
   *         (the specifications for which accidentals are applied to which notes)
   */
  public KeySignature getKeySignature() {
    return keySignature;
  }

  /**
   * Returns the Mode of this key.
   * @return thigetByNoteAtDegrees.mode (starting note of this note's scale or ordered sequence of pitches)
   */
  public Mode getMode() {
    return mode;
  }

  /**
   * Determines if the pitch is in the key.
   * @param pitch the pitch to be compared to the key
   * @return a boolean indicating if the pitch is in the key
   */
  public boolean isInKey(Pitch pitch) {
    // Check if pitch is associated with the correct accidental
    for (int i = 0; i < notesInAScale; i++) {
      if (scale.get(i).equals(pitch.getScaleValue())) {
        Accidental correctAccidental = this.keySignature.getAccidentals()
            .get((i + mode.getOffset()) % notesInAScale);
        return pitch.getAccidental().equals(correctAccidental);
      }
    }
    // Should never get here because all elements of ScaleValue are iterated through and checked
    throw new InvalidScaleException();
  }

  /**
   * Determines if the pitch is in the key.
   * @param note the note whose pitch is to be compared to the key
   * @return a boolean indicating if the pitch is in the key
   */
  public boolean isInKey(Note note) {
    return isInKey(note.getPitch());
  }

  /**
   * Determines if a note is the leading tone, or the 7th degree, of a scale.
   * Will return true as long as the ScaleValue is correct.
   * @param note the note to be analyzed
   * @return whether note is the leading tone
   */
  public boolean isLeadingTone(Note note) {
    return note.getPitch().getScaleValue().equals(scale.get(6));
  }

  /**
   * Returns the ScaleDegree of a pitch (its position on a scale).
   * @param pitch the pitch whose scale position (or lack thereof) will be determined
   * @return the ScaleDegree of the pitch in question
   */
  public ScaleDegree getScaleDegree(Pitch pitch) {
    int scaleIndex = 0;
    // find the position of the pitch
    while (!scale.get(scaleIndex).equals(pitch.getScaleValue())) {
      scaleIndex++;
    }
    // Check if the pitch is in the key
    if (isInKey(pitch)) {
      return ScaleDegree.values()[scaleIndex];
    }
    //Throw an error if it isn't
    throw new PitchNotInScaleException();
  }

  /**
   * Returns the ScaleDegree of a pitch (its position on a scale).
   * @param note the note whose pitch whose scale position (or lack thereof) will be determined
   * @return the ScaleDegree of the pitch in question
   */
  public ScaleDegree getScaleDegree(Note note) {
    return getScaleDegree(note.getPitch());
  }

  /**
   * From a ScaleDegree, gets the Pitch from a specified octave corresponding to it.
   * @param sd a ScaleDegree
   * @param octave the desired octave for the new pitch
   * @return the pitch at that ScaleValue
   */
  public Pitch getPitch(ScaleDegree sd, int octave) {
    return new Pitch(scale.get(sd.getDegree()),
        octave,
        keySignature.getAccidentals().get((mode.getOffset() + sd.getDegree()) % notesInAScale));
  }

  @Override
  public String toString() {
    String accidentalString = keySignature.getAccidentals().get(mode.getOffset()).toString();
    return scale.get(0) + accidentalString + mode;
  }
}
