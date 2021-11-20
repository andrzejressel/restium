package com.restium.impl.fastmail.integration

import io.kotest.core.annotation.EnabledCondition
import io.kotest.core.spec.Spec
import kotlin.reflect.KClass

class LinuxOnlyCondition : EnabledCondition {
  override fun enabled(specKlass: KClass<out Spec>): Boolean = System.getenv().run {
    val username = getOrDefault("FASTMAIL_USERNAME", "")
    val password = getOrDefault("FASTMAIL_PASSWORD", "")
    username.isNotBlank() && password.isNotBlank()
  }
}
