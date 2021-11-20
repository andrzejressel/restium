package com.restium.impl.fastmail.usecase

import com.restium.impl.fastmail.JmapFastmailAPI
import com.restium.impl.fastmail.model.MaskedEmailId
import com.restium.impl.fastmail.model.Session

class RemoveMaskedEmailUseCase(
  private val jmapFastmailAPI: JmapFastmailAPI
) {
  suspend operator fun invoke(session: Session, id: MaskedEmailId) {
    jmapFastmailAPI.removeMaskedEmail(session, id)
  }
}
