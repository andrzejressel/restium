package com.andrzejressel.browserapi.impl.fastmail

import com.andrzejressel.browserapi.api.WebsiteImplementation
import com.andrzejressel.browserapi.impl.fastmail.model.Configuration
import com.andrzejressel.browserapi.impl.fastmail.usecase.useCaseModule
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.dsl.single

@Suppress("EXPERIMENTAL_API_USAGE")
class EntryPoint {

  private val koinApplication: KoinApplication

  inner class FastMailComponent : KoinComponent {
    override fun getKoin(): Koin = this@EntryPoint.koinApplication.koin
    val fastMailWebsiteImplementation by inject<FastMailWebsiteImplementation>()
  }
  
  private val fastMailModule = module {
    single<FastMailWebsiteImplementation>()
    single<JmapFastmailAPI>()
  } + useCaseModule

  init {
    koinApplication = koinApplication {
      // use Koin logger
      printLogger()
      // declare modules
      modules(fastMailModule)
    }
  }

  fun createWebsiteImplementation(): WebsiteImplementation<Configuration> {
    return FastMailComponent().fastMailWebsiteImplementation
  }
}