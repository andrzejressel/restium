package com.restium.jmap;

import com.restium.jmap.entity.MaskedEmail;
import com.restium.jmap.method.call.maskedemail.GetMaskedEmailMethodCall;
import com.restium.jmap.method.response.maskedemail.GetMaskedEmailMethodResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import rs.ltt.jmap.client.JmapClient;

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
    Assertions.assertThat(response.getList()).containsExactly(
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
