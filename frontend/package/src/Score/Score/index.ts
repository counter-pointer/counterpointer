import Clef from "../Clef";
import Key, { KeyJSON } from "../Key";
import Mode from "../Key/Mode";
import Melody, { MelodyJSON } from "../Melody";
import Role from "./Role";

interface ScoreJSON {
  melody: MelodyJSON,
  counterMelody: MelodyJSON,
  melodyIsUpper: boolean,
}

class Score {
  key: Key;
  melody: Melody;
  counterMelody: Melody;
  isMelodyHigher: boolean;

  constructor(key: Key, isMelodyHigher: boolean) {
    this.key = key;
    this.melody = new Melody([],new Key(key.keySignature, key.mode));
    this.counterMelody = new Melody([],new Key(key.keySignature, key.mode));
    this.isMelodyHigher = isMelodyHigher
  }

  setKey(newKey: Key) {
      this.key = newKey;
      this.melody.key = newKey;
      this.counterMelody.key = newKey;
  }

  getClefByRole(role: Role): Clef {
    if (role === Role.MELODY) return Clef.ALTO;
    return (this.isMelodyHigher) ? Clef.BASS : Clef.TREBLE;
  }

  getMelodyByRole(role: Role): Melody {
    if (role == Role.MELODY) {
      return this.melody
    }
    else {
      return this.counterMelody
    }
  }

  setMelodyByRole(role: Role, melody: Melody) {
    if (role == Role.MELODY) {
      this.melody = melody;
    }
    else {
      this.counterMelody = melody
    }
  }

  private _getABCForRole(role : Role): string {
    return Role.toABC(role) + " " + Clef.toABC(this.getClefByRole(role)) + "\n";
  }

  getABCMapping(): [string, Map<number, [Role, number]> , Map<Role, Map<number, number>>] {
    let stringAcc: string = "";
    stringAcc += "K:" + this.key.toABC() + "\n";
    if (this.isMelodyHigher) {
      stringAcc += this._getABCForRole(Role.MELODY) + this._getABCForRole(Role.COUNTERMELODY);
    } else {
      stringAcc += this._getABCForRole(Role.COUNTERMELODY) + this._getABCForRole(Role.MELODY);
    }

    const [melodyString, melodyMap] = this.melody.getABCMapping(Role.MELODY, stringAcc.length);
    stringAcc += melodyString + '\n';
    const [cmString, cmMap] = this.counterMelody.getABCMapping(Role.COUNTERMELODY, stringAcc.length); //plus one for the line break.
    stringAcc += cmString;
    const mMapFlat : Array<[number, [Role, number]]> = Array.from(melodyMap.entries()).map(([abcIndex, index]) => [abcIndex, [Role.MELODY, index]]);
    const cmMapFlat : Array<[number, [Role, number]]> = Array.from(cmMap.entries()).map(([abcIndex, index]) => [abcIndex, [Role.COUNTERMELODY, index]]);

    const map = new Map([...mMapFlat, ...cmMapFlat]);
    const reverseMap = new Map([[Role.MELODY, new Map()], [Role.COUNTERMELODY, new Map()]]);
    //make the mapping from the reverse mapping
    map.forEach(([role, index], abcIndex) => {
      reverseMap.get(role)!.set(index, abcIndex);
    });

    return [stringAcc, map, reverseMap];
  }

  static fromJSON(o: ScoreJSON): Score {
    let score = new Score(Key.fromJSON(o.melody.key), o.melodyIsUpper);
    score.melody = Melody.fromJSON(o.melody);
    score.counterMelody = Melody.fromJSON(o.counterMelody);
    return score;
  }
}

export default Score;