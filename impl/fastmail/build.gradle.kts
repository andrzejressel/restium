plugins {
    id("demo.kotlin-common-conventions")
}

version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":api"))
    implementation(project(":libs:jmap"))
    implementation("io.insert-koin:koin-core:${Versions.koin_version}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-guava:1.5.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
    implementation("org.seleniumhq.selenium:selenium-support:${Versions.selenium_version}")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}