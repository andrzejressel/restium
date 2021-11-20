package com.andrzejressel.browserapi.impl.fastmail

import com.andrzejressel.browserapi.api.Browser
import com.andrzejressel.browserapi.impl.fastmail.model.Configuration
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import java.util.concurrent.CompletableFuture

object WebsitePreparator {
    fun prepare(browser: Browser, configuration: Configuration): CompletableFuture<Void> {
        return CompletableFuture.runAsync {
            val webDriver = browser.chromeDriver
            val wait = WebDriverWait(webDriver, Duration.ofSeconds(4))
            val userNameInput = By.xpath("//input[@name='username']")
            val passwordInput = By.xpath("//input[@name='password']")
            val loginButton = By.xpath("//button[text()='Log In']")

            webDriver.navigate().to("https://www.fastmail.com/login/")
            wait.until(ExpectedConditions.visibilityOfElementLocated(userNameInput))
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput))
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton))

            webDriver.findElement(userNameInput).sendKeys(configuration.username)
            webDriver.findElement(passwordInput).sendKeys(configuration.password)
            webDriver.findElement(loginButton).click()
        }
    }
}