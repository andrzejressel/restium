package com.restium.impl.fastmail.model

typealias MaskedEmailJmapDto = com.restium.jmap.entity.MaskedEmail

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

data class MaskedEmailDto(
  val id: String,
  val description: String,
  val state: String,
  val email: String,
  val createdBy: String,
  val createdAt: String,
  val url: String?,
  val forDomain: String,
)

fun MaskedEmailJmapDto.toModel() = MaskedEmail(
  id = MaskedEmailId(id),
  description = description,
  state = state,
  email = email,
  createdBy = createdBy,
  createdAt = createdAt,
  url = url,
  forDomain = forDomain
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
