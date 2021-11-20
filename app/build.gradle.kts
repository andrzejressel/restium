plugins {
  id("demo.scala-common-conventions")
  application
}

repositories {
  mavenCentral()
}

val circeVersion = "0.14.1"
val fs2Version = "3.2.2"
val catsRetryVersion = "3.1.0"
val http4sVersion = "0.23.6"
val seleniumVersion = "4.0.0"
val jcodecVersion = "0.2.5"
val catsEffectVersion = "3.2.9"

dependencies {
  implementation(project(":api"))

  implementation("com.google.guava:guava:30.1.1-jre")

  implementation("org.seleniumhq.selenium:selenium-api:$seleniumVersion")
  implementation("org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion")
  implementation("org.seleniumhq.selenium:selenium-devtools-v95:$seleniumVersion")
  implementation("org.seleniumhq.selenium:selenium-support:$seleniumVersion")

  implementation("org.typelevel:cats-effect_2.13:${catsEffectVersion}")

  listOf("core", "generic", "parser", "literal").forEach {
    implementation("io.circe:circe-${it}_2.13:${circeVersion}")
  }

  listOf("core", "io", "reactive-streams", "scodec").forEach {
    implementation("co.fs2:fs2-${it}_2.13:${fs2Version}")
  }

  implementation("com.github.cb372:cats-retry_2.13:$catsRetryVersion")

  listOf("dsl", "circe", "blaze-client").forEach {
    implementation("org.http4s:http4s-${it}_2.13:${http4sVersion}")
  }

  implementation(project(":api"))
  implementation(project(":impl:fastmail"))
  implementation("rs.ltt.jmap:jmap-client:0.7.4")

  testImplementation("junit:junit:4.13.2")
  testImplementation("org.scalatest:scalatest_2.13:3.2.9")
  testImplementation("org.scalatestplus:junit-4-13_2.13:3.2.2.0")
  testRuntimeOnly("org.scala-lang.modules:scala-xml_2.13:1.2.0")
}

application {
  mainClass.set("com.restium.app.Main")
}
