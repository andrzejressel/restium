package rs.ltt.jmap.examples;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

abstract class AbstractJmapTest {

  protected static final String ACCOUNT_ID = "u00000000";
  protected static final String USERNAME = "u00000000";
  protected static final String PASSWORD = "secret";
  protected static final String WELL_KNOWN_PATH = ".well-known/jmap";
  protected MockWebServer server;

  @SuppressWarnings("UnstableApiUsage")
  protected static String readResourceAsString(String filename) throws IOException {
    return Resources.asCharSource(Resources.getResource(filename), Charsets.UTF_8).read().trim();
  }

  @BeforeEach
  void setup() throws Exception {
    server = new MockWebServer();
    server.start();
    server.enqueue(new MockResponse().setBody(readResourceAsString("01-session.json")));
  }

  @AfterEach
  void cleanup() throws Exception {
    server.shutdown();
  }

}