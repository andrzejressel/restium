plugins {
  id("demo.java-common-conventions")
}

dependencies {
  api("rs.ltt.jmap:jmap-client:0.7.4")
  annotationProcessor("rs.ltt.jmap:jmap-annotation-processor:0.7.4")

  compileOnly("org.projectlombok:lombok:1.18.22")
  annotationProcessor("org.projectlombok:lombok:1.18.22")

  implementation("org.slf4j:slf4j-simple:1.7.32")

  testImplementation("com.squareup.okhttp3:mockwebserver:4.9.2")
}
