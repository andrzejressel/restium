package com.andrzejressel.browserapi.impl.fastmail.model

typealias MaskedEmailRequestDto = rs.ltt.jmap.examples.entity.CreateMaskedEmailRequest

data class MaskedEmailRequest(
    val state: String = "enabled",
    val description: String,
    val url: String? = null,
    val emailPrefix: String = "",
) {
    fun toDto() = MaskedEmailRequestDto(state, description, url, emailPrefix)
}
