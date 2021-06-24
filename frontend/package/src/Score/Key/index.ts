import Note from "../Note";
import Pitch from "../Pitch";
import Accidental from "../Pitch/Accidental";
import ScaleValue from "../Pitch/ScaleValue";
import KeySignature, { KeySignatureJSON } from "./KeySignature";
import KeySignatures from "./KeySignatures";
import Mode from "./Mode";
import ScaleDegree from "./ScaleDegree";

export interface KeyJSON {
  keySignature: KeySignatureJSON,
  mode: Mode,
}

class Key {
  keySignature: KeySignature
  mode: Mode

  private NOTES_IN_A_SCALE = 7;
  private _scale: Array<ScaleValue>

  constructor(keySignature: KeySignature, mode: Mode) {
    this.keySignature = keySignature
    this.mode = mode
    //keySignature tonic + (mode offset % 7) = "actual tonic"
    const tonic = ScaleValue.values()[this.keySignature.ionianTonic
      + this.mode % this.NOTES_IN_A_SCALE]
    this._scale = this.keySignature.getScaleLetterNames(tonic)
  }

  static fromString(s: string): Key {
    if (s.length < 4 || s.length > 5) {
      throw new Error('Invalid key string');
    }
    let modeStr: string = s.slice(s.length - 3, s.length);
    let tonicAccStr: string = s.slice(0, s.length - 3);
    let tonicStr: string = tonicAccStr.slice(0, 1);
    let accStr: string = tonicAccStr.slice(1, s.length - 3);
    let mode = Mode.fromString(modeStr);
    return new Key(KeySignatures.fromEncoding(ScaleValue.fromString(tonicStr), Accidental.fromString(accStr), mode), mode);
  }

  isPitchInKey(p: Pitch): boolean {
    let correctAccidental: Accidental = this.keySignature.accidentals[(p.scaleValue - this.keySignature.ionianTonic + this.NOTES_IN_A_SCALE) % this.NOTES_IN_A_SCALE];
    return p.accidental == correctAccidental;
  }

  isNoteInKey(n: Note): boolean {
    if (n.pitch === null) return true;
    return this.isPitchInKey(n.pitch);
  }

  isPitchLeadingTone(p: Pitch): boolean {
    return p.scaleValue == this._scale[6];
  }

  isNoteLeadingTone(n: Note): boolean {
    if (n.pitch === null) return false;
    return this.isPitchLeadingTone(n.pitch);
  }

  getPitchScaleDegree(p: Pitch): ScaleDegree {
    if (this.isPitchInKey(p)) {
      return ScaleDegree.values()[p.scaleValue]
    }
    throw new Error("ERROR: Pitch is not in scale! (getPitchScaleDegree)")
  }

  getNoteScaleDegree(n: Note): ScaleDegree | null {
    if (n.pitch === null) return null;
    return this.getPitchScaleDegree(n.pitch);
  }

  getScaleValue(sd: ScaleDegree): ScaleValue {
    return this._scale[sd];
  }

  accidentalFromScaleValue(sv: ScaleValue): Accidental {
    return this.keySignature.accidentals[(sv - this.keySignature.ionianTonic + this.NOTES_IN_A_SCALE) % this.NOTES_IN_A_SCALE];
  }

  addAccidentalToKey(ac: Accidental, sv: ScaleValue) {
    //For bar logic within the Melody toABC, modifies the key at a specific scale value to include the new accidental
    this.keySignature.accidentals[(sv - this.keySignature.ionianTonic + this.NOTES_IN_A_SCALE) % this.NOTES_IN_A_SCALE] = ac;
  }

  toString(): string {
    return `${ScaleValue.toString(this.getScaleValue(ScaleDegree.TONIC))}${Accidental.toString(this.keySignature.accidentals[this.mode])}${Mode.toString(this.mode)}`;
  }

  toABC(): string {
    return this.toString();
  }

  toJSON(): Object {
    return {
      keySignature: this.keySignature,
      mode: this.mode,
    };
  }

  static fromJSON(o: KeyJSON) {
    return new Key(KeySignature.fromJSON(o.keySignature), o.mode);
  }
}

export default Key;