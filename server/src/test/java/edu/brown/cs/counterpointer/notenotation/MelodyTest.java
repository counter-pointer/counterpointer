package edu.brown.cs.counterpointer.notenotation;

import edu.brown.cs.counterpointer.notenotation.exceptions.BeatOutOfBoundsException;
import edu.brown.cs.counterpointer.notenotation.exceptions.EndBeatGreatThanBeginBeatException;
import edu.brown.cs.counterpointer.notenotation.key.Key;
import edu.brown.cs.counterpointer.notenotation.pitch.Pitch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static edu.brown.cs.counterpointer.notenotation.Duration.EIGHTH;
import static edu.brown.cs.counterpointer.notenotation.Duration.HALF;
import static edu.brown.cs.counterpointer.notenotation.Duration.QUARTER;
import static edu.brown.cs.counterpointer.notenotation.Duration.WHOLE;
import static edu.brown.cs.counterpointer.notenotation.key.KeySignatures.FIVE_FLAT;
import static edu.brown.cs.counterpointer.notenotation.key.KeySignatures.SEVEN_FLAT;
import static edu.brown.cs.counterpointer.notenotation.key.KeySignatures.SIX_SHARP;
import static edu.brown.cs.counterpointer.notenotation.key.Mode.DORIAN;
import static edu.brown.cs.counterpointer.notenotation.key.Mode.MIXOLYDIAN;
import static edu.brown.cs.counterpointer.notenotation.pitch.Accidental.NATURAL;
import static edu.brown.cs.counterpointer.notenotation.pitch.ScaleValue.B;
import static edu.brown.cs.counterpointer.notenotation.pitch.ScaleValue.C;
import static edu.brown.cs.counterpointer.notenotation.pitch.ScaleValue.F;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

public class MelodyTest {
  // notes
  Note f = new Note(new Pitch(F, 0, NATURAL), WHOLE);
  Note b = new Note(new Pitch(B, 0, NATURAL), WHOLE);
  Note c1 = new Note(new Pitch(C, 1, NATURAL), HALF);
  Note quarterRest = new Note(null, QUARTER);
  Note halfRest = new Note(null, HALF);

  // example melodies mostly quarters, but one 8th, one half
  Melody empty = new Melody(
      Collections.emptyList(),
      new Key(SIX_SHARP, DORIAN),
      QUARTER
  );
  Melody johnCage = new Melody(
      Arrays.asList(quarterRest, quarterRest, halfRest),
      new Key(FIVE_FLAT, MIXOLYDIAN),
      QUARTER
  );
  Melody dPhr = new Melody(
      Arrays.asList(f, c1, b),
      new Key(FIVE_FLAT, MIXOLYDIAN),
      HALF
  );
  Melody minimalist = new Melody(
      Arrays.asList(f, new Note(new Pitch(F, 0, NATURAL), WHOLE), f),
      new Key(FIVE_FLAT, MIXOLYDIAN),
      EIGHTH
  );

  // Get test on all but the empty
  @Test
  public void getTests() throws BeatOutOfBoundsException {
    // basic testing
    assertEquals(f, dPhr.at(2));
    assertEquals(c1, dPhr.at(5));
    // right where c1 begins
    assertEquals(c1, dPhr.at(4));
    // Very important for consecutive identical note identification:
    assertEquals(minimalist.at(0), minimalist.at(2));
    // Works on rests
    assertEquals(johnCage.at(1.5), quarterRest);
    assertEquals(new Melody(Collections.singletonList(halfRest),
        new Key(SEVEN_FLAT, DORIAN), QUARTER).at(1), halfRest);
    // throws error when out of bounds
    assertThrows(BeatOutOfBoundsException.class, () -> empty.at(0));
  }

  @Test
  public void subMelodyTests() {
    // Exceptions
    assertThrows(EndBeatGreatThanBeginBeatException.class,
        () -> johnCage.subMelody(.75, .25));
    assertThrows(BeatOutOfBoundsException.class, () -> empty.subMelody(-1.65, -0.04));

    // Normal behavior
    assertEquals(johnCage.subMelody(.25, 1).getKey().getKeySignature().getAccidentals(),
        johnCage.getKey().getKeySignature().getAccidentals());
    assertEquals(johnCage.subMelody(.25, 1).getNotes().toString(),
        Collections.singletonList(quarterRest).toString());
    assertEquals(johnCage.subMelody(.25, .75).getKey().getKeySignature().getAccidentals(),
        johnCage.getKey().getKeySignature().getAccidentals());
    assertEquals(johnCage.subMelody(.25, .75).getNotes().toString(),
        Collections.singletonList(quarterRest).toString());

    // A range that will return empty
    assertEquals(empty.subMelody(0, 0).getNotes().size(), 0);

    // Get multiple notes
    assertEquals(dPhr.subMelody(0, 10).getNotes().toString(), dPhr.getNotes().toString());
    assertEquals(dPhr.subMelody(0, 0).getNotes().size(), 0);

    // Starting on the second note
    assertEquals(dPhr.subMelody(4, 10).getNotes().toString(), Arrays.asList(c1, b).toString());
    assertEquals(dPhr.subMelody(4, 6).getNotes().toString(), Arrays.asList(c1).toString());
  }

  @Test
  public void lengthTests() {
    assertEquals(0, empty.length(), 0.0);
    assertEquals(4, johnCage.length(), 0.0);
    assertEquals(10, dPhr.length(), 0.0);
    assertEquals(12, minimalist.length(), 0.0);
  }
}
