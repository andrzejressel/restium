package com.andrzejressel.browserapi.impl.fastmail

import com.andrzejressel.browserapi.api.Browser
import com.andrzejressel.browserapi.api.WebsiteImplementation
import com.andrzejressel.browserapi.impl.fastmail.model.Configuration
import com.andrzejressel.browserapi.impl.fastmail.usecase.UseCaseCoordinator
import com.google.common.util.concurrent.Atomics
import java.util.concurrent.CompletableFuture

class FastMailWebsiteImplementation(
    val useCaseCoordinator: UseCaseCoordinator
) : WebsiteImplementation<Configuration> {

    private val websitePreparator = WebsitePreparator
    private val configuration = Atomics.newReference<Configuration>()

    override fun prepare(browser: Browser): CompletableFuture<Void> {
        val configuration = configuration.get()
        return if (configuration == null) {
            CompletableFuture.failedFuture(IllegalStateException("Configuration is not set"))
        } else {
            websitePreparator.prepare(browser, configuration)
        }
    }

    override fun invokeMethod(browser: Browser, methodId: Any, body: ByteArray?): CompletableFuture<Any> =
        useCaseCoordinator(browser, body, methodId as UseCaseType)

    override fun getConfigurationClass(): Class<Configuration> = Configuration::class.java

    override fun setConfiguration(configuration: Configuration): CompletableFuture<Void> {
        return CompletableFuture.runAsync {
            this.configuration.set(configuration)
        }
    }

}