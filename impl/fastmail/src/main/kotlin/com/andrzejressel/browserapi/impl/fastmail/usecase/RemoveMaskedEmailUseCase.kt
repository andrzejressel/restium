package com.andrzejressel.browserapi.impl.fastmail.usecase

import com.andrzejressel.browserapi.impl.fastmail.JmapFastmailAPI
import com.andrzejressel.browserapi.impl.fastmail.model.MaskedEmailId
import com.andrzejressel.browserapi.impl.fastmail.model.MaskedEmailRequest
import com.andrzejressel.browserapi.impl.fastmail.model.Session

class RemoveMaskedEmailUseCase(
    private val jmapFastmailAPI: JmapFastmailAPI
) {
    suspend operator fun invoke(session: Session, id: MaskedEmailId) {
        jmapFastmailAPI.removeMaskedEmail(session, id)
    }
}