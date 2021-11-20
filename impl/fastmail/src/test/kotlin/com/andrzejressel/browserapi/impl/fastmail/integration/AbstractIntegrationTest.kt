package com.andrzejressel.browserapi.impl.fastmail.integration

import com.andrzejressel.browserapi.api.Browser
import com.andrzejressel.browserapi.impl.fastmail.JmapFastmailAPI
import com.andrzejressel.browserapi.impl.fastmail.WebsitePreparator
import com.andrzejressel.browserapi.impl.fastmail.model.Configuration
import com.andrzejressel.browserapi.impl.fastmail.model.Session
import com.andrzejressel.browserapi.impl.fastmail.usecase.UseCaseCoordinator.Companion.getSession
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