import Duration from "../Score/Duration";
import Key from "../Score/Key";
import Note from "../Score/Note";
import Accidental from "../Score/Pitch/Accidental";
import ScaleValue from "../Score/Pitch/ScaleValue";
import Role from "../Score/Score/Role";
import Score from "../Score";
import Species from "./Species";
import Pitch from "../Score/Pitch";
import ScaleDegree from "../Score/Key/ScaleDegree";
import KeySignature from "../Score/Key/KeySignature";
import Mode from "../Score/Key/Mode";

class Editor {
  private score: Score;
  private species: Species;

  private abc: string;
  private mapping: Map<number, [Role, number]>;
  private reverseMapping: Map<Role, Map<number, number>>;
  private selectedIndex: number | null;

  private static TREBLE_OCTAVE = 5;
  private static ALTO_OCTAVE = 4;
  private static BASS_OCTAVE = 3;

  constructor(score: Score, species: Species) {
    this.score = score;
    this.species = species;

    // make sure score is not empty: otherwise, insert default notes
    if (this.score.counterMelody.notes.length === 0 || this.score.melody.notes.length === 0) {
      if (this.score.counterMelody.notes.length === 0) {
        this._addNote(this.score.key.getScaleValue(ScaleDegree.TONIC), Role.COUNTERMELODY, null);
      }
      if (this.score.melody.notes.length === 0) {
        this._addNote(this.score.key.getScaleValue(ScaleDegree.TONIC), Role.MELODY, null);
      }
    }

    this._updateMapping(Role.MELODY, 0);
  }

  selectNoteABC(i: number) {
    if (!this.mapping.has(i)) return;
    this.selectedIndex = i;
  }

  selectNote(role: Role, i: number) {
    if (this.reverseMapping.get(role)!.has(i)) {
      this.selectedIndex = this.reverseMapping.get(role)!.get(i)!;
    }
  }

  getSelectedNote(): Note | null {
    if (this.selectedIndex === null) return null;
    const [role, noteIndex] = this.mapping.get(this.selectedIndex)!;
    const note = this.score.getMelodyByRole(role).notes[noteIndex];
    return note;
  }

  getSelectedRole(): Role | null {
    if (this.selectedIndex === null) return null;
    return this.mapping.get(this.selectedIndex)![0];
  }

  deselect() {
    this.selectedIndex = null;
  }

  getSelectedIndexABC(): number {
    if (this.selectedIndex === null) return 0;
    return this.selectedIndex;
  }

  shouldSuggest(): boolean {
    return this.score.melody.length() !== this.score.counterMelody.length();
  }

  addSuggestion(note : Note) {
    const melody = this.score.getMelodyByRole(Role.COUNTERMELODY);
    melody.append(note);
    this._updateMapping(Role.COUNTERMELODY, melody.notes.length - 1);
  }

  selectLeft() {
    if (this.selectedIndex === null) return;
    const [role, noteIndex] = this.mapping.get(this.selectedIndex)!;
    if (noteIndex > 0) {
      this.selectNote(role, noteIndex - 1);
    }
  }

  selectRight() {
    if (this.selectedIndex === null) return;
    const [role, noteIndex] = this.mapping.get(this.selectedIndex)!;
    const melody = this.score.getMelodyByRole(role);
    if (noteIndex < (melody.notes.length - 1)) {
      this.selectNote(role, noteIndex + 1);
    }
  }

  private _updateMapping(role : Role, noteIndex : number) {
    [this.abc, this.mapping, this.reverseMapping] = this.score.getABCMapping();
    this.selectNote(role, noteIndex);
  }

  raiseSelectedNote() {
    if (this.selectedIndex === null) return;
    const [role, noteIndex] = this.mapping.get(this.selectedIndex)!;
    const note = this.score.getMelodyByRole(role).notes[noteIndex];
    if (note.pitch === null) return;
    note.pitch = Pitch.raise(note.pitch);
    this._updateMapping(role, noteIndex);
  }

  lowerSelectedNote() {
    if (this.selectedIndex === null) return;
    const [role, noteIndex] = this.mapping.get(this.selectedIndex)!;
    const note = this.score.getMelodyByRole(role).notes[noteIndex];
    if (note.pitch === null) return;
    note.pitch = Pitch.lower(note.pitch);
    this._updateMapping(role, noteIndex);
  }

  sharpSelectedNote() {
    if (this.selectedIndex === null) return;
    const [role, noteIndex] = this.mapping.get(this.selectedIndex)!;
    const note = this.score.getMelodyByRole(role).notes[noteIndex];
    if (note.pitch === null) return;
    note.pitch.accidental = Accidental.raise(note.pitch.accidental);
    this._updateMapping(role, noteIndex);
  }

  flatSelectedNote() {
    if (this.selectedIndex === null) return;
    const [role, noteIndex] = this.mapping.get(this.selectedIndex)!;
    const note = this.score.getMelodyByRole(role).notes[noteIndex];
    if (note.pitch === null) return;
    note.pitch.accidental = Accidental.lower(note.pitch.accidental);
    this._updateMapping(role, noteIndex);
  }

  raiseOctaveSelectedNote() {
    if (this.selectedIndex === null) return;
    const [role, noteIndex] = this.mapping.get(this.selectedIndex)!;
    const note = this.score.getMelodyByRole(role).notes[noteIndex];
    if (note.pitch === null) return;

    // TODO: octave limit based on species
    note.pitch.octave++;

    this._updateMapping(role, noteIndex);
  }
  
  lowerOctaveSelectedNote() {
    if (this.selectedIndex === null) return;
    const [role, noteIndex] = this.mapping.get(this.selectedIndex)!;
    const note = this.score.getMelodyByRole(role).notes[noteIndex];
    if (note.pitch === null) return;

    // TODO: octave limit based on species
    note.pitch.octave--;

    this._updateMapping(role, noteIndex);
  }

  changeDurationSelectedNote(duration: Duration) {
    if (this.selectedIndex === null) return;
    const [role, noteIndex] = this.mapping.get(this.selectedIndex)!;
    const note = this.score.getMelodyByRole(role).notes[noteIndex];
    if (note.pitch === null) return;

    note.duration = duration;

    this._updateMapping(role, noteIndex);
  }

  //get octave based on clef of the selected note's melody;
  //get accidentals based on the key;
  //get duration based on the current species (if editing countermelody)
  addScaleValueAtSelected(sv: ScaleValue | null) {
    if (this.selectedIndex === null) return;
    const [role, noteIndex] = this.mapping.get(this.selectedIndex)!;
    this._addNote(sv, role, noteIndex + 1);
  }

  addNoteAtSelected(note: Note) {
    if (this.selectedIndex === null) return;
    const [role, noteIndex] = this.mapping.get(this.selectedIndex)!;
    const melody = this.score.getMelodyByRole(role);
    melody.insert(note, noteIndex + 1);
    this._updateMapping(role, noteIndex + 1);
  }

  deleteSelectedNote() {
    if (this.selectedIndex === null) return;
    const [role, noteIndex] = this.mapping.get(this.selectedIndex)!;
    const melody = this.score.getMelodyByRole(role);

    // refuse to delete the last note
    if (melody.notes.length <= 1) {
      return;
    }

    melody.delete(noteIndex);
    const nextIndex = Math.max(0, noteIndex - 1);
    this._updateMapping(role, nextIndex);
  }

  private _getOctaveByRole(role : Role): number {
    if (role === Role.MELODY) {
      return Editor.ALTO_OCTAVE;
    } else {
      if (this.score.isMelodyHigher) {
        return Editor.BASS_OCTAVE;
      } else {
        return Editor.TREBLE_OCTAVE;
      }
    }
  }

  private _getDefaultDuration(): Duration {
    switch (this.species) {
      case 1:
        return Duration.WHOLE;
      case 2:
        return Duration.HALF;
      case 3:
        return Duration.QUARTER;
      case 4:
        return Duration.WHOLE;
      default:
        return Duration.WHOLE;
    }
  }

  private _addNote(sv: ScaleValue | null, role: Role, noteIndex: number | null) {
    const melody = this.score.getMelodyByRole(role);
    const pitch : Pitch | null = sv === null ? null : new Pitch(sv, this._getOctaveByRole(role), this.score.key.accidentalFromScaleValue(sv));
    melody.insert(new Note(pitch, this._getDefaultDuration()), noteIndex === null ? 0 : noteIndex);
    if (noteIndex !== null) {
      this._updateMapping(role, noteIndex);
    } else {
      this._updateMapping(role, 0);
    }
  }

  setKey(key: Key) {
    const oldMapping = this.mapping;
    this.score.key = key;
    [this.abc, this.mapping, this.reverseMapping] = this.score.getABCMapping();
    if (this.selectedIndex === null) {
      return;
    };
    const [role, noteIndex] = oldMapping.get(this.selectedIndex)!;
    this.selectedIndex = this.reverseMapping.get(role)!.get(noteIndex)!;
  }

  setKeySignature(keySignature: KeySignature) {
    this.setKey(new Key(keySignature, this.score.key.mode));
  }

  setMode(mode: Mode) {
    this.setKey(new Key(this.score.key.keySignature, mode));
  }

  setScore(score: Score) {
    this.score = score;
    const oldMapping = this.mapping;
    [this.abc, this.mapping, this.reverseMapping] = this.score.getABCMapping();

    if (this.selectedIndex === null) return;
    const [role, noteIndex] = oldMapping.get(this.selectedIndex)!;
    this.selectedIndex = this.reverseMapping.get(role)!.get(noteIndex)!;
  }

  clearMelodyByRole(role: Role) {
    const melody = this.score.getMelodyByRole(role);
    melody.notes = [];
    this._addNote(this.score.key.getScaleValue(ScaleDegree.TONIC), role, null);
  }

  toABC(): string {
    return this.abc;
  }
}

export default Editor