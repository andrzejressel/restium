package com.restium.impl.fastmail.model

data class RemoveMaskedEmailRequest(
  val id: String
) {
  fun toDto() = MaskedEmailId(id)
}
