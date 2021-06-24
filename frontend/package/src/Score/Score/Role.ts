import { SSL_OP_TLS_ROLLBACK_BUG } from "node:constants"

enum Role {
  MELODY,
  COUNTERMELODY,
}

namespace Role {
  export function toABC(role: Role){
    if (role == Role.MELODY) {
      return "V:M"
    } else { //is countermelody
      return "V:CM"
    }
  }
}

export default Role