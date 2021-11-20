plugins {
  id("demo.jvm-common-conventions")
  kotlin("jvm")
}

dependencies {
  // Align versions of all Kotlin components
  implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

  implementation(kotlin("stdlib"))
  implementation(kotlin("reflect"))

  testImplementation("io.kotest:kotest-runner-junit5:4.6.3")
  testImplementation("io.kotest:kotest-assertions-core:4.6.3")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions.jvmTarget = "11"
}