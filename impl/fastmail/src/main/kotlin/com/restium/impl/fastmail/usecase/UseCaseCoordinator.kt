package com.restium.impl.fastmail.usecase

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.restium.api.Browser
import com.restium.impl.fastmail.UseCaseType
import com.restium.impl.fastmail.UseCaseType.*
import com.restium.impl.fastmail.model.AccessToken
import com.restium.impl.fastmail.model.RemoveMaskedEmailRequest
import com.restium.impl.fastmail.model.Session
import com.restium.impl.fastmail.model.UserId
import kotlinx.coroutines.*
import kotlinx.coroutines.future.asCompletableFuture
import java.util.concurrent.CompletableFuture

class UseCaseCoordinator(
  private val getMaskedEmailUseCase: GetMaskedEmailUseCase,
  private val createMaskedEmailUseCase: CreateMaskedEmailUseCase,
  private val removeMaskedEmailUseCase: RemoveMaskedEmailUseCase
) {

  private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
  private val objectMapper = jacksonObjectMapper()

  operator fun invoke(browser: Browser, body: ByteArray?, type: UseCaseType): CompletableFuture<Any> =
    scope.async {
      val session = browser.getSession()

      when (type) {
        GET_MASKED_EMAIL -> getMaskedEmailUseCase(session)
        CREATE_MASKED_EMAIL -> {
          val localBody = body
            ?: throw IllegalArgumentException("For $CREATE_MASKED_EMAIL body must not be null")
          createMaskedEmailUseCase.invoke(session, objectMapper.readValue(localBody))
        }
        REMOVE_MASKED_EMAIL -> {
          val localBody = body
            ?: throw IllegalArgumentException("For $CREATE_MASKED_EMAIL body must not be null")
          removeMaskedEmailUseCase.invoke(
            session,
            objectMapper.readValue<RemoveMaskedEmailRequest>(localBody).toDto()
          )
        }
      }

    }.asCompletableFuture()

  companion object {

    suspend fun Browser.getSession(): Session {
      val sessions = getSessionMap()
      return Session(
        accessToken = AccessToken(sessions["accessToken"] as String),
        userId = UserId(sessions["userId"] as String)
      )
    }

    private suspend fun Browser.getSessionMap(): Map<String, Any> {
      lateinit var ex: Exception
      repeat(5) {
        try {
          delay(500)
          return jacksonObjectMapper().readValue(chromeDriver.localStorage.getItem("sessions"))
        } catch (e: Exception) {
          ex = e
        }
      }
      throw ex
    }
  }

}
