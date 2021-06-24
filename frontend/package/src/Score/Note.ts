import Duration from "./Duration";
import Key from "./Key";
import Pitch, { PitchJSON } from "./Pitch";

export interface NoteJSON {
  pitch: PitchJSON;
  duration: Duration;
}

class Note {
  pitch: Pitch | null;
  duration: Duration;

  constructor(pitch: Pitch | null, dur: Duration) {
    this.pitch = pitch;
    this.duration = dur;
  }

  isRest(): boolean {
    return this.pitch === null;
  }

  toABC(key: Key): string {
    if (this.pitch == null) {
      return "z" + Duration.toABC(this.duration)
    }
    else {
      return this.pitch.toABC(key) + Duration.toABC(this.duration);
    }
    
  }

  static fromJSON(o: NoteJSON): Note {
    return new Note(Pitch.fromJSON(o.pitch), o.duration);
  }

}

export default Note;