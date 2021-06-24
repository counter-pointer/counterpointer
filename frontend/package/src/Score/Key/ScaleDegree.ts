enum ScaleDegree {
    TONIC = 0,
    SUPERTONIC,
    MEDIANT,
    SUBDOMINANT,
    DOMINANT,
    SUBMEDIANT,
    LEADINGTONE,
}

namespace ScaleDegree {
  export function values(): Array<ScaleDegree> {
    return [ScaleDegree.TONIC, ScaleDegree.SUPERTONIC, ScaleDegree.MEDIANT, ScaleDegree.SUBDOMINANT, 
      ScaleDegree.DOMINANT, ScaleDegree.SUBMEDIANT, ScaleDegree.LEADINGTONE]
  }
}

export default ScaleDegree;