package com.andrzejressel.browserapi.impl.fastmail.usecase

import com.andrzejressel.browserapi.impl.fastmail.JmapFastmailAPI
import com.andrzejressel.browserapi.impl.fastmail.model.MaskedEmail
import com.andrzejressel.browserapi.impl.fastmail.model.Session

class GetMaskedEmailUseCase(
    private val jmapFastmailAPI: JmapFastmailAPI
) {
    suspend operator fun invoke(session: Session): List<MaskedEmail> {
        return jmapFastmailAPI.getMaskedEmails(session)
    }
}