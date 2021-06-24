enum Mode {
  IONIAN = 0,
  DORIAN = 1,
  PHRYRGIAN = 2,
  MIXOLYDIAN = 4,
  AEOLIAN = 5,
}

namespace Mode {
  export function toString(m : Mode): string {
    switch (m) {
      case Mode.IONIAN:
        return 'maj';

      case Mode.DORIAN:
        return 'dor';
      
      case Mode.PHRYRGIAN:
        return 'phr';
      
      case Mode.MIXOLYDIAN:
        return 'mix';
        
      case Mode.AEOLIAN:
        return 'aeo';
        
      default:
        return 'maj';
    }
  }

  export function fromString(s : string): Mode {
    switch (s) {
      case 'maj':
        return Mode.IONIAN;

      case 'dor':
        return Mode.DORIAN;
      
      case 'phr':
        return Mode.PHRYRGIAN;
      
      case 'mix':
        return Mode.MIXOLYDIAN;
        
      case 'aeo':
        return Mode.AEOLIAN;
        
      default:
        return Mode.IONIAN;
    }
  }
}

export default Mode;