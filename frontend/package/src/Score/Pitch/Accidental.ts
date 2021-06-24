enum Accidental {
  NAT = 0,
  SHARP = 1,
  DBLSHARP = 2,
  FLAT = -1,
  DBLFLAT = -2,
}

namespace Accidental {
  export function raise(a: Accidental): Accidental {
    switch (a) {
      case Accidental.DBLFLAT:
        return Accidental.FLAT;
      case Accidental.FLAT:
        return Accidental.NAT;
      case Accidental.NAT:
        return Accidental.SHARP;
      case Accidental.SHARP:
        return Accidental.DBLSHARP;
      default:
        return a;
    }
  }

  export function lower(a: Accidental): Accidental {
    switch (a) {
      case Accidental.FLAT:
        return Accidental.DBLFLAT;
      case Accidental.NAT:
        return Accidental.FLAT;
      case Accidental.SHARP:
        return Accidental.NAT;
      case Accidental.DBLSHARP:
        return Accidental.SHARP;
      default:
        return a;
    }
  }

  export function toString(a: Accidental): string {
    switch (a) {
      case Accidental.NAT:
        return '';
      case Accidental.SHARP:
        return '#';
      case Accidental.DBLSHARP:
        return '##';
      case Accidental.FLAT:
        return 'b';
      case Accidental.DBLFLAT:
        return 'bb';
      default:
        return '';
    }
  }

  export function toStringPretty(a: Accidental): string {
    switch (a) {
      case Accidental.NAT:
        return '';
      case Accidental.SHARP:
        return '#';
      case Accidental.DBLSHARP:
        return '𝄪';
      case Accidental.FLAT:
        return '♭';
      case Accidental.DBLFLAT:
        return '♭♭';
      default:
        return '';
    }
  }

  export function toABC(a: Accidental): string {
    switch (a) {
      case Accidental.NAT:
        return '=';
      case Accidental.SHARP:
        return '^';
      case Accidental.DBLSHARP:
        return '^^';
      case Accidental.FLAT:
        return '_';
      case Accidental.DBLFLAT:
        return '__';
      default:
        return '';
    };
  }

  export function fromString(s: string): Accidental {
    switch (s) {
      case '':
        return Accidental.NAT;
      case '#':
        return Accidental.SHARP;
      case '##':
        return Accidental.DBLSHARP;
      case 'b':
        return Accidental.FLAT;
      case 'bb':
        return Accidental.DBLFLAT;
      default:
        return Accidental.NAT;
    }
  }
}
export default Accidental;