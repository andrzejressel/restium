package com.restium.impl.fastmail.model

import com.restium.jmap.entity.SetMaskedEmailRequest

typealias MaskedEmailRequestDto = SetMaskedEmailRequest

data class CreateMaskedEmailRequest(
  val description: String
) {
  private val state: String = "enabled"
  private val url: String? = null
  private val emailPrefix: String = ""

  fun toDto() = MaskedEmailRequestDto(state, description, url, emailPrefix)
}
