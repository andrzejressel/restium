plugins {
  java
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

repositories {
  mavenCentral()
}

dependencies {
  testImplementation("org.junit.jupiter:junit-jupiter:${Versions.junit_version}")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${Versions.junit_version}")
}

tasks.named<Test>("test") {
  useJUnitPlatform()
}
