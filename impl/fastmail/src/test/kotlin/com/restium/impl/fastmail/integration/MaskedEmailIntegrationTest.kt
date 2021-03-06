package com.restium.impl.fastmail.integration

import com.restium.impl.fastmail.model.CreateMaskedEmailRequest
import com.restium.impl.fastmail.usecase.CreateMaskedEmailUseCase
import com.restium.impl.fastmail.usecase.GetMaskedEmailUseCase
import com.restium.impl.fastmail.usecase.RemoveMaskedEmailUseCase
import io.kotest.core.annotation.EnabledIf
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldEndWith

@EnabledIf(LinuxOnlyCondition::class)
class MaskedEmailIntegrationTest : AbstractIntegrationTest({

  val createMaskedEmailUseCase = CreateMaskedEmailUseCase(api)
  val getMaskedEmailUseCase = GetMaskedEmailUseCase(api)
  val removeMaskedEmailUseCase = RemoveMaskedEmailUseCase(api)

  test("Should create and remove masked email") {
    // given
    val createRequest = CreateMaskedEmailRequest(description = "New masked email")

    // when
    val newMaskedEmailId = createMaskedEmailUseCase(session, createRequest)

    // and
    val maskedEmails = getMaskedEmailUseCase(session)

    // then
    val newMaskedEmail = maskedEmails.first { it.id==newMaskedEmailId }
    newMaskedEmail.id shouldBe newMaskedEmailId
    newMaskedEmail.description shouldBe "New masked email"
    newMaskedEmail.email shouldEndWith "@fastmail.com"

    // when
    removeMaskedEmailUseCase(session, newMaskedEmailId)

    // and
    val maskedEmails2 = getMaskedEmailUseCase(session)

    // then
    val newMaskedEmail2 = maskedEmails2.find { it.id==newMaskedEmailId }
    newMaskedEmail2 shouldBe null

  }

})
