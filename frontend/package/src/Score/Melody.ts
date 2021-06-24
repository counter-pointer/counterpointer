import Key, { KeyJSON } from "./Key";
import Note, { NoteJSON } from "./Note";
import Role from "./Score/Role";

export interface MelodyJSON {
  notes: Array<NoteJSON>;
  key: KeyJSON;
}

class Melody {
  notes: Array<Note>;
  key: Key;

  constructor(notes: Array<Note>, key: Key) {
    this.notes = notes;
    this.key = key; 
  }

  getNote(i: number): Note {
    return this.notes[i];
  }

  //Appends a new note n to the current list of notes
  append(n: Note) {
    this.notes.push(n);
  }

  //inserts a new Note at the given index.
  insert(n: Note, i: number) {
    this.notes.splice(i, 0, n);
  }

  delete(i: number) {
    if (i < 0 || i >= this.notes.length) return;
    this.notes.splice(i, 1);
  }

  length(): number {
    return this.notes.map(note => note.duration).reduce((a, b) => a+b, 0);
  }

  // each entry is a bar in ABCJS and the mapping of the index of the ABC string to its corresponding Note index (in the list of all notes)
  // @param name - the name of the voice part (probably melody, countermelody)
  getABCMapping(role: Role, startI: number, barsPerLine: number = 8): [string, Map<number, number>]{
    const BEATS_PER_BAR = 4;

    let accMap: Map<number, number> = new Map();
    let accStr = '';

    let curBar = barsPerLine;

    // deep copy the key
    let curKey = Key.fromJSON(JSON.parse(JSON.stringify(this.key)));
    let curBeat = 0;

    // deep copy the current notes
    const notes : Array<Note> = this.notes.map(note => Note.fromJSON(JSON.parse(JSON.stringify(note))));

    if (notes.length === 0) {
      return ["[" + Role.toABC(role) + "]|", new Map()];
    }

    // for loop across all notes; create map, start adding to map as you add new notes.
    for (let i = 0; i < notes.length; i++) {
      // end a new line if necessary
      if (curBar >= barsPerLine) {
        let newLineStr = '';
        // add new line if this is not the beginning of the first line
        if (accStr !== '') {
          newLineStr += '\n';
        }
        newLineStr += "[" + Role.toABC(role) + "]";
        accStr += newLineStr;
        curBar = 0;
      }
      
      // register new note on map
      accMap.set(startI + accStr.length, i);
      const curNote = notes[i];
      // edge case: has to tie note across a barline
      if (curBeat + curNote.duration > BEATS_PER_BAR) {
        // makes two dummy notes, where both map to the same Array<Note> index.
        const firstNote = new Note(curNote.pitch, BEATS_PER_BAR - curBeat);
        const secondNote = new Note(curNote.pitch, curNote.duration - (BEATS_PER_BAR - curBeat));
        
        // add first note and tie
        accStr += firstNote.toABC(curKey) + '-';
        
        // replace current note with the second note and ask the loop to render the second note
        notes[i] = secondNote;
        i--;
        curBeat = BEATS_PER_BAR;
      } else { //THE NORMAL CASE: note is applied to a single bar.
        //in the Pitch.toABC(), curKey will mutate to house the new accidental if applicable
        accStr += curNote.toABC(curKey);
        curBeat += curNote.duration;
      }

      // end a bar if necessary
      if (curBeat >= BEATS_PER_BAR) {
        accStr += '|';
        curKey = Key.fromJSON(JSON.parse(JSON.stringify(this.key)));
        curBeat = 0;
        curBar++;
      }
    }
    accStr += '|' // to make a double bar line end
    return [accStr, accMap];
  }

  static fromJSON(o: MelodyJSON): Melody {
    return new Melody(o.notes.map(Note.fromJSON), Key.fromJSON(o.key));
  }
}

export default Melody;