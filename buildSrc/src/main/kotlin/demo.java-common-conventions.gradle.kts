plugins {
  id("demo.jvm-common-conventions")
  `java-library`
}

dependencies {
  testImplementation("org.assertj:assertj-core:${Versions.assertj_version}")
}