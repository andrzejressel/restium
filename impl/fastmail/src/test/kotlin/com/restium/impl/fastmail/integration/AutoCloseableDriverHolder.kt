package com.restium.impl.fastmail.integration

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class AutoCloseableDriverHolder : AutoCloseable {

  val chromeDriver: ChromeDriver

  init {
    val options = ChromeOptions()
    options.addArguments("--lang=en", "--disable-dev-shm-usage", "--headless")
    chromeDriver = ChromeDriver(options)
  }

  override fun close() {
    chromeDriver.close()
  }

}
