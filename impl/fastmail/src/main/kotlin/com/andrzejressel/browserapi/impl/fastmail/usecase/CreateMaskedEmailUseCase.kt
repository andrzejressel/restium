package com.andrzejressel.browserapi.impl.fastmail.usecase

import com.andrzejressel.browserapi.impl.fastmail.JmapFastmailAPI
import com.andrzejressel.browserapi.impl.fastmail.model.MaskedEmailId
import com.andrzejressel.browserapi.impl.fastmail.model.MaskedEmailRequest
import com.andrzejressel.browserapi.impl.fastmail.model.Session

class CreateMaskedEmailUseCase(
    private val jmapFastmailAPI: JmapFastmailAPI
) {
    suspend operator fun invoke(session: Session, request: MaskedEmailRequest): MaskedEmailId {
        return jmapFastmailAPI.createMaskedEmail(session, request)
    }
}