plugins {
    id("demo.java-common-conventions")
}

version = "unspecified"

dependencies {
    api("org.seleniumhq.selenium:selenium-chrome-driver:${Versions.selenium_version}")
}
