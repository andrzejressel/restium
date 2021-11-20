package com.restium.service.model

import com.restium.api.Browser
import org.openqa.selenium.chrome.ChromeDriver

case class BrowserImpl(private val chromeDriver: ChromeDriver) extends Browser {
  override def getChromeDriver: ChromeDriver = chromeDriver
}
