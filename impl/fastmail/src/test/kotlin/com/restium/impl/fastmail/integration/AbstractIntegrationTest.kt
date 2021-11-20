package com.restium.impl.fastmail.integration

import com.restium.api.Browser
import com.restium.impl.fastmail.JmapFastmailAPI
import com.restium.impl.fastmail.WebsitePreparator
import com.restium.impl.fastmail.model.Configuration
import com.restium.impl.fastmail.model.Session
import com.restium.impl.fastmail.usecase.UseCaseCoordinator.Companion.getSession
import io.kotest.core.spec.style.FunSpec
import kotlinx.coroutines.future.await

abstract class AbstractIntegrationTest(body: AbstractIntegrationTest.() -> Unit = {}) : FunSpec() {

  private val websiteImplementation = autoClose(AutoCloseableDriverHolder())

  val api = JmapFastmailAPI()
  lateinit var browser: Browser
  lateinit var session: Session

  init {
    beforeEach {
      browser = Browser { websiteImplementation.chromeDriver }
      val username = System.getenv("FASTMAIL_USERNAME")
      val password = System.getenv("FASTMAIL_PASSWORD")
      val configuration = Configuration(username, password)
      WebsitePreparator.prepare(browser, configuration).await()
      session = browser.getSession()
    }
    body()
  }

}
