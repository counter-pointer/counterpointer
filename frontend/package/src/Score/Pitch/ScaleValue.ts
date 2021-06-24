enum ScaleValue {
  C = 0,
  D,
  E,
  F,
  G,
  A,
  B,
}
/**
 * Alternate: (if ever needed, maybe more complex object?)
 * C: 0
 * D: 2
 * E: 4
 * F: 5
 * G: 7
 * A: 9
 * B: 11
 */

namespace ScaleValue {
  export function raise(sv: ScaleValue): ScaleValue {
    switch (sv) {
      case ScaleValue.C:
        return ScaleValue.D;
      case ScaleValue.D:
        return ScaleValue.E;
      case ScaleValue.E:
        return ScaleValue.F;
      case ScaleValue.F:
        return ScaleValue.G;
      case ScaleValue.G:
        return ScaleValue.A;
      case ScaleValue.A:
        return ScaleValue.B;
      default:
        return sv;
    }
  }

  export function lower(sv: ScaleValue): ScaleValue {
    switch (sv) {
      case ScaleValue.D:
        return ScaleValue.C;
      case ScaleValue.E:
        return ScaleValue.D;
      case ScaleValue.F:
        return ScaleValue.E;
      case ScaleValue.G:
        return ScaleValue.F;
      case ScaleValue.A:
        return ScaleValue.G;
      case ScaleValue.B:
        return ScaleValue.A;
      default:
        return sv;
    }
  }

  export function toString(sv: ScaleValue): string {
    return ScaleValue[sv];
  }

  export function toABC(sv: ScaleValue): string {
    return toString(sv);
  }

  export function fromString(s: string): ScaleValue {
    switch (s) {
      case "C":
        return ScaleValue.C;
      case "D":
        return ScaleValue.D;
      case "E":
        return ScaleValue.E;
      case "F":
        return ScaleValue.F;
      case "G":
        return ScaleValue.G;
      case "A":
        return ScaleValue.A;
      case "B":
        return ScaleValue.B;
      default:
        return ScaleValue.C;
    }
  }

  export function values(): Array<ScaleValue> {
    return [ScaleValue.C, ScaleValue.D, ScaleValue.E, ScaleValue.F, ScaleValue.G, ScaleValue.A, ScaleValue.B]
  }
}

export default ScaleValue;