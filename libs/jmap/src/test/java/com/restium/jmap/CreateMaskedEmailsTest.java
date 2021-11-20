package com.restium.jmap;

import com.google.common.collect.ImmutableMap;
import com.restium.jmap.entity.CreateMaskedEmailRequest;
import com.restium.jmap.entity.CreatedMaskedEmail;
import com.restium.jmap.method.call.maskedemail.SetMaskedEmailMethodCall;
import com.restium.jmap.method.response.maskedemail.CreateMaskedEmailMethodResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;
import rs.ltt.jmap.client.JmapClient;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateMaskedEmailsTest extends AbstractJmapTest {

  @Test
  public void getPlaceholder() throws Exception {
    // given
    final JmapClient jmapClient = new JmapClient(
      USERNAME,
      PASSWORD,
      server.url(WELL_KNOWN_PATH)
    );

    server.enqueue(new MockResponse().setBody(readResourceAsString("create-maskedemail/02-response.json")));

    var maskedEmail = new CreateMaskedEmailRequest("enabled", "Test from API", null, "");

    // when
    var response = jmapClient
      .call(new SetMaskedEmailMethodCall(ACCOUNT_ID, null, ImmutableMap.of("k380", maskedEmail), null, null, null))
      .get()
      .getMain(CreateMaskedEmailMethodResponse.class);

    server.takeRequest(); //session
    RecordedRequest placeholderRequest = server.takeRequest(); //placeholder

    // then
    assertThat(response.getCreated().values())
      .containsExactly(
        new CreatedMaskedEmail("kind.home9295@fastmail.com", "", "2021-11-17T22:38:10Z", null, "")
      );

    assertThat(placeholderRequest.getBody().inputStream())
      .hasContent(readResourceAsString("create-maskedemail/02-request.json"));
  }

}
