package com.andrzejressel.browserapi.impl.fastmail

import com.andrzejressel.browserapi.impl.fastmail.model.*
import kotlinx.coroutines.guava.await
import rs.ltt.jmap.client.JmapClient
import rs.ltt.jmap.examples.BearerTokenHttpAuthentication
import rs.ltt.jmap.examples.method.call.maskedemail.GetMaskedEmailMethodCall
import rs.ltt.jmap.examples.method.call.maskedemail.SetMaskedEmailMethodCall
import rs.ltt.jmap.examples.method.response.maskedemail.CreateMaskedEmailMethodResponse
import rs.ltt.jmap.examples.method.response.maskedemail.GetMaskedEmailMethodResponse

@Suppress("UnstableApiUsage")
class JmapFastmailAPI {

  suspend fun createMaskedEmail(session: Session, request: MaskedEmailRequest): MaskedEmailId {
    return useJmapClient(session) { jmapClient ->
      val id = "000"
      val response = jmapClient
        .call(
          SetMaskedEmailMethodCall(
            "u${session.userId.value}",
            null,
            mapOf(id to request.toDto()),
            null, null, null
          )
        )
        .await()
        .getMain(CreateMaskedEmailMethodResponse::class.java)

      val created = response.created?.get(id)
      val error = response.notCreated?.get(id)

      if (created != null) {
        MaskedEmailId(created.id)
      } else if (error != null) {
        throw Exception("Cannot create masked email: ${error.description}")
      } else {
        throw IllegalStateException("Cannot deserialize response")
      }
    }
  }

  suspend fun getMaskedEmails(session: Session): List<MaskedEmail> {
    return useJmapClient(session) { jmapClient ->
      jmapClient
        .call(GetMaskedEmailMethodCall("u${session.userId.value}"))
        .await()
        .getMain(GetMaskedEmailMethodResponse::class.java)
        .list
        .map { it.toModel() }
    }
  }

  suspend fun removeMaskedEmail(session: Session, maskedEmailId: MaskedEmailId) {
    return useJmapClient(session) { jmapClient ->
      jmapClient
        .call(
          SetMaskedEmailMethodCall(
            "u${session.userId.value}",
            null,
            null,
            null,
            arrayOf(maskedEmailId.value),
            null
          )
        )
        .await()
        .getMain(CreateMaskedEmailMethodResponse::class.java)
    }
  }

  private inline fun <T> useJmapClient(session: Session, f: (JmapClient) -> T): T {
    // @api.fastmail.com for automatic endpoint finding
    return JmapClient(
      BearerTokenHttpAuthentication("${session.userId.value}@api.fastmail.com", session.accessToken.value)
    ).use { f(it) }
  }

}