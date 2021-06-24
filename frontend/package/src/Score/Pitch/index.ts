import Key from "../Key"
import Accidental from "./Accidental"
import ScaleValue from "./ScaleValue"

export interface PitchJSON {
  scaleValue: ScaleValue;
  octave: number;
  accidental: Accidental;
}

class Pitch {
  scaleValue: ScaleValue
  octave: number
  accidental: Accidental

  constructor(scaleValue: ScaleValue, octave: number, accidental: Accidental) {
      this.scaleValue = scaleValue
      this.octave = octave
      this.accidental = accidental
  }


  toABC(key: Key): string {
      //if accidental of pitch is in key:
      if (this.accidental == key.accidentalFromScaleValue(this.scaleValue)) {
        return ScaleValue.toABC(this.scaleValue) + Pitch._octavetoABC(this.octave);
      }
      else {
        //For Melody nonsense, mutate Key to include new accidental for this bar.
        key.addAccidentalToKey(this.accidental, this.scaleValue);
        return Accidental.toABC(this.accidental) + ScaleValue.toABC(this.scaleValue) + Pitch._octavetoABC(this.octave)
      }
      //if not, either notate natural, sharp, flat, depending on sit.
  }

  toString(): String {
    return ScaleValue.toString(this.scaleValue) + Accidental.toStringPretty(this.accidental) + this.octave;
  }

  static raise(pitch: Pitch): Pitch {
    let octave : number;
    let scaleValue : ScaleValue;
    if (pitch.scaleValue == ScaleValue.B) {
      octave = pitch.octave + 1;
      scaleValue = ScaleValue.C;
    } else {
      octave = pitch.octave;
      scaleValue = ScaleValue.raise(pitch.scaleValue);
    }
    return new Pitch(scaleValue, octave, pitch.accidental);
  }

  static lower(pitch: Pitch): Pitch {
    let octave : number;
    let scaleValue : ScaleValue;
    if (pitch.scaleValue == ScaleValue.C) {
      octave = pitch.octave - 1;
      scaleValue = ScaleValue.B;
    } else {
      octave = pitch.octave;
      scaleValue = ScaleValue.lower(pitch.scaleValue);
    }
    return new Pitch(scaleValue, octave, pitch.accidental);
  }


  static fromJSON(o: PitchJSON | null): Pitch | null {
    if (o === null) return null;
    return new Pitch(o.scaleValue, o.octave, o.accidental);
  }

  private static _octavetoABC(octave: number): string  {
    // captial scalevalues = X4, (C = C4) 
    
    const numRepeat: number = octave - 4;
    if (numRepeat < 0) {
      // 1 oct down: ,
      return ",".repeat(-numRepeat);
    }
    else {
      // 1 oct up: ' , 
      return "'".repeat(numRepeat);
    }
  }

}

export default Pitch;