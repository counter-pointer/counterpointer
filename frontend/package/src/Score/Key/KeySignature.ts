import Accidental from "../Pitch/Accidental";
import ScaleValue from "../Pitch/ScaleValue";

export interface KeySignatureJSON {
  accidentals: Array<Accidental>;
  ionianTonic: ScaleValue;
}

//Cross-implementation from the KeySignature.js backend.
class KeySignature {
  accidentals: Array<Accidental>
  ionianTonic: ScaleValue

  constructor(accidentals: Array<Accidental>, ionianTonic: ScaleValue) {
    this.accidentals = accidentals
    this.ionianTonic = ionianTonic
  }


  // Cross-implementation from backend;
  // given a tonic scalevalue, returns the 
  // ordered list of scalevalues that make
  // up the key.
  getScaleLetterNames(tonic: ScaleValue): Array<ScaleValue> {
    const NOTES_IN_A_SCALE = 7;
    // split the scale up into two fragments, the one tonic and above, and the
    // one below tonic
    let firstSegment: Array<ScaleValue> = ScaleValue.values().splice(tonic, NOTES_IN_A_SCALE)
    let lastSegment: Array<ScaleValue> = ScaleValue.values().splice(0, tonic)

    return firstSegment.concat(lastSegment);
  }

  static fromJSON(o: KeySignatureJSON): KeySignature {
    return new KeySignature(o.accidentals, o.ionianTonic);
  }
}

export default KeySignature;