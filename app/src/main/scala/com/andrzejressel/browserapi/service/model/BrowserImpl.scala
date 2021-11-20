package com.andrzejressel.browserapi.service.model

import com.andrzejressel.browserapi.api.Browser
import org.openqa.selenium.chrome.ChromeDriver

case class BrowserImpl(private val chromeDriver: ChromeDriver) extends Browser {
  override def getChromeDriver: ChromeDriver = chromeDriver
}
