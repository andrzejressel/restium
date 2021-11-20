package com.restium.impl.fastmail.usecase

import com.restium.impl.fastmail.JmapFastmailAPI
import com.restium.impl.fastmail.model.CreateMaskedEmailRequest
import com.restium.impl.fastmail.model.MaskedEmailId
import com.restium.impl.fastmail.model.Session

class CreateMaskedEmailUseCase(
  private val jmapFastmailAPI: JmapFastmailAPI
) {
  suspend operator fun invoke(session: Session, request: CreateMaskedEmailRequest): MaskedEmailId {
    return jmapFastmailAPI.createMaskedEmail(session, request)
  }
}
