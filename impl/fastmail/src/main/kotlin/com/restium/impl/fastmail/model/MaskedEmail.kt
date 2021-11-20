package com.restium.impl.fastmail.model

typealias MaskedEmailDto = com.restium.jmap.entity.MaskedEmail

data class MaskedEmail(
  val id: MaskedEmailId,
  val description: String,
  val state: String,
  val email: String,
  val createdBy: String,
  val createdAt: String,
  val url: String?,
  val forDomain: String,
)

fun MaskedEmailDto.toModel() = MaskedEmail(
  id = MaskedEmailId(id),
  description = description,
  state = state,
  email = email,
  createdBy = createdBy,
  createdAt = createdAt,
  url = url,
  forDomain = forDomain
)
