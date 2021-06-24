enum Clef {
  TREBLE = 0,
  ALTO,
  BASS,
  //is this the intended enumeration? Any reason to be more savvy than this?
}

//type Clef = typeof ClefEnum[keyof typeof ClefEnum];

namespace Clef {
  export function toABC(c: Clef): string {
    switch (c) {
      case (Clef.TREBLE):
        return "clef=treble";
      case (Clef.ALTO):
        return "clef=alto";
      case (Clef.BASS):
        return "clef=bass";
    }
  }
}


export default Clef;