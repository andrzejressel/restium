package rs.ltt.jmap.examples;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;
import rs.ltt.jmap.client.JmapClient;
import rs.ltt.jmap.examples.entity.MaskedEmail;
import rs.ltt.jmap.examples.method.call.maskedemail.GetMaskedEmailMethodCall;
import rs.ltt.jmap.examples.method.response.maskedemail.GetMaskedEmailMethodResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

public class GetMaskedEmailsTest extends AbstractJmapTest {

  @Test
  public void getPlaceholder() throws IOException, ExecutionException, InterruptedException {
    // given
    final JmapClient jmapClient = new JmapClient(
      USERNAME,
      PASSWORD,
      server.url(WELL_KNOWN_PATH)
    );

    server.enqueue(new MockResponse().setBody(readResourceAsString("get-maskedemail/02-response.json")));

    // when
    var response = jmapClient
      .call(new GetMaskedEmailMethodCall(ACCOUNT_ID))
      .get()
      .getMain(GetMaskedEmailMethodResponse.class);

    server.takeRequest(); //session
    RecordedRequest placeholderRequest = server.takeRequest(); //placeholder

    // then
    assertThat(response.getList()).containsExactly(
      new MaskedEmail(
        "Masked Email Example",
        "enabled",
        "kind.bell1113@fastmail.com",
        "",
        "2021-11-09T16:14:54Z",
        null,
        "fastmail.com"
      )
    );
    assertThat(placeholderRequest.getBody().inputStream())
      .hasContent(readResourceAsString("get-maskedemail/02-request.json"));
  }

}