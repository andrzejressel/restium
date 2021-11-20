package com.restium.impl.fastmail.usecase

import org.koin.core.annotation.KoinReflectAPI
import org.koin.dsl.module
import org.koin.dsl.single

@KoinReflectAPI
val useCaseModule = module {
  single<UseCaseCoordinator>()

  single<CreateMaskedEmailUseCase>()
  single<GetMaskedEmailUseCase>()
  single<RemoveMaskedEmailUseCase>()
}
