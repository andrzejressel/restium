package com.restium.impl.fastmail.model

import com.restium.jmap.entity.CreateMaskedEmailRequest

typealias MaskedEmailRequestDto = CreateMaskedEmailRequest

data class MaskedEmailRequest(
  val state: String = "enabled",
  val description: String,
  val url: String? = null,
  val emailPrefix: String = "",
) {
  fun toDto() = MaskedEmailRequestDto(state, description, url, emailPrefix)
}
