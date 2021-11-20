package com.restium.impl.fastmail

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.FunSpec

class EntryPointTest : FunSpec({

  test("Should create multiple entry points") {
    // when
    EntryPoint()

    // then
    shouldNotThrowAny { EntryPoint() }
  }

})
