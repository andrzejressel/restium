package com.restium.impl.fastmail.usecase

import com.restium.impl.fastmail.JmapFastmailAPI
import com.restium.impl.fastmail.model.MaskedEmail
import com.restium.impl.fastmail.model.Session

class GetMaskedEmailUseCase(
  private val jmapFastmailAPI: JmapFastmailAPI
) {
  suspend operator fun invoke(session: Session): List<MaskedEmail> {
    return jmapFastmailAPI.getMaskedEmails(session)
  }
}
