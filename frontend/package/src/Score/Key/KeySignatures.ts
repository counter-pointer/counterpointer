import Accidental from "../Pitch/Accidental";
import ScaleValue from "../Pitch/ScaleValue";
import KeySignature from "./KeySignature";
import Mode from './Mode';

class KeySignatures {
  static readonly SEVEN_FLAT: KeySignature = new KeySignature(
    [Accidental.FLAT, Accidental.FLAT, Accidental.FLAT, Accidental.FLAT, Accidental.FLAT , Accidental.FLAT, Accidental.FLAT], 
    ScaleValue.C)

  static readonly SIX_FLAT: KeySignature = new KeySignature(
      [Accidental.FLAT, Accidental.FLAT, Accidental.FLAT, Accidental.FLAT, Accidental.FLAT , Accidental.FLAT, Accidental.NAT], 
      ScaleValue.G)

  static readonly FIVE_FLAT: KeySignature = new KeySignature(
    [Accidental.FLAT, Accidental.FLAT, Accidental.NAT, Accidental.FLAT, Accidental.FLAT , Accidental.FLAT, Accidental.NAT], 
    ScaleValue.D)

  static readonly FOUR_FLAT: KeySignature = new KeySignature(
    [Accidental.FLAT, Accidental.FLAT, Accidental.NAT, Accidental.FLAT, Accidental.FLAT , Accidental.NAT, Accidental.NAT], 
    ScaleValue.A)

  static readonly THREE_FLAT: KeySignature = new KeySignature(
    [Accidental.FLAT, Accidental.NAT, Accidental.NAT, Accidental.FLAT, Accidental.FLAT , Accidental.NAT, Accidental.NAT], 
    ScaleValue.E)

  static readonly TWO_FLAT: KeySignature = new KeySignature(
    [Accidental.FLAT, Accidental.NAT, Accidental.NAT, Accidental.FLAT, Accidental.NAT , Accidental.NAT, Accidental.NAT], 
    ScaleValue.B)

  static readonly ONE_FLAT: KeySignature = new KeySignature(
    [Accidental.NAT, Accidental.NAT, Accidental.NAT, Accidental.FLAT, Accidental.NAT , Accidental.NAT, Accidental.NAT], 
    ScaleValue.F)

  static readonly NO_ACCIDENTAL: KeySignature = new KeySignature(
    [Accidental.NAT, Accidental.NAT, Accidental.NAT, Accidental.NAT, Accidental.NAT , Accidental.NAT, Accidental.NAT], 
    ScaleValue.C)
  
  static readonly ONE_SHARP: KeySignature = new KeySignature(
    [Accidental.NAT, Accidental.NAT, Accidental.NAT, Accidental.NAT, Accidental.NAT , Accidental.NAT, Accidental.SHARP], 
    ScaleValue.G)
 
  static readonly TWO_SHARP: KeySignature = new KeySignature(
    [Accidental.NAT, Accidental.NAT, Accidental.SHARP, Accidental.NAT, Accidental.NAT , Accidental.NAT, Accidental.SHARP], 
    ScaleValue.D)

  static readonly THREE_SHARP: KeySignature = new KeySignature(
    [Accidental.NAT, Accidental.NAT, Accidental.SHARP, Accidental.NAT, Accidental.NAT , Accidental.SHARP, Accidental.SHARP], 
    ScaleValue.A)

  static readonly FOUR_SHARP: KeySignature = new KeySignature(
    [Accidental.NAT, Accidental.SHARP, Accidental.SHARP, Accidental.NAT, Accidental.NAT , Accidental.SHARP, Accidental.SHARP], 
    ScaleValue.E)

  static readonly FIVE_SHARP: KeySignature = new KeySignature(
    [Accidental.NAT, Accidental.SHARP, Accidental.SHARP, Accidental.NAT, Accidental.SHARP , Accidental.SHARP, Accidental.SHARP], 
    ScaleValue.B)

  static readonly SIX_SHARP: KeySignature = new KeySignature(
    [Accidental.SHARP, Accidental.SHARP, Accidental.SHARP, Accidental.NAT, Accidental.SHARP , Accidental.SHARP, Accidental.SHARP], 
    ScaleValue.F)

  static readonly SEVEN_SHARP: KeySignature = new KeySignature(
    [Accidental.SHARP, Accidental.SHARP, Accidental.SHARP, Accidental.SHARP, Accidental.SHARP , Accidental.SHARP, Accidental.SHARP], 
    ScaleValue.C)

  //~~~

  static readonly ALL_KEY_SIGNATURES: Array<KeySignature> = 
    [KeySignatures.SEVEN_FLAT, KeySignatures.SIX_FLAT, KeySignatures.FIVE_FLAT, KeySignatures.FOUR_FLAT, KeySignatures.THREE_FLAT, KeySignatures.TWO_FLAT, KeySignatures.ONE_FLAT, KeySignatures.NO_ACCIDENTAL, 
      KeySignatures.ONE_SHARP, KeySignatures.TWO_SHARP, KeySignatures.THREE_SHARP, KeySignatures.FOUR_SHARP, KeySignatures.FIVE_SHARP, KeySignatures.SIX_SHARP, KeySignatures.SEVEN_SHARP]

  static fromEncoding(sv : ScaleValue, a : Accidental, m : Mode): KeySignature {
    for (let k of this.ALL_KEY_SIGNATURES) {
      let noteNameScale = k.getScaleLetterNames(k.ionianTonic);

      if (noteNameScale[m] == sv && k.accidentals[m] == a) {
        return k;
      }
    }
    return this.NO_ACCIDENTAL;
  }

  //~~~

  private constructor() {
  }
  
}

export default KeySignatures;