enum Duration {
    QUARTER = 1.0,
    HALF = 2.0,
    WHOLE = 4.0,
}

namespace Duration { 
    export function toABC(d: Duration): string {
        return (Math.trunc(d) * 2).toString();
    }
}

export default Duration;