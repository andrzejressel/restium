package rs.ltt.jmap.examples.method.response.maskedemail;

import rs.ltt.jmap.annotation.JmapMethod;
import rs.ltt.jmap.common.method.response.standard.GetMethodResponse;
import rs.ltt.jmap.examples.entity.MaskedEmail;

@JmapMethod("MaskedEmail/get")
public class GetMaskedEmailMethodResponse extends GetMethodResponse<MaskedEmail> {
  public GetMaskedEmailMethodResponse(String accountId, String state, String[] notFound, MaskedEmail[] list) {
    super(accountId, state, notFound, list);
  }
}
