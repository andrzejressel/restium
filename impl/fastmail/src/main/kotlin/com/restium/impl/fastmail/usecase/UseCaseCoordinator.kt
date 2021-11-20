package com.restium.impl.fastmail.usecase

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.restium.api.Browser
import com.restium.impl.fastmail.UseCaseType
import com.restium.impl.fastmail.UseCaseType.GET_MASKED_EMAIL
import com.restium.impl.fastmail.model.AccessToken
import com.restium.impl.fastmail.model.Session
import com.restium.impl.fastmail.model.UserId
import kotlinx.coroutines.*
import kotlinx.coroutines.future.asCompletableFuture
import java.util.concurrent.CompletableFuture

class UseCaseCoordinator(
  val getMaskedEmailUseCase: GetMaskedEmailUseCase
) {

  private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

  operator fun invoke(browser: Browser, body: ByteArray?, type: UseCaseType): CompletableFuture<Any> = scope.async {
    val session = browser.getSession()

    when (type) {
      GET_MASKED_EMAIL -> getMaskedEmailUseCase(session)
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
