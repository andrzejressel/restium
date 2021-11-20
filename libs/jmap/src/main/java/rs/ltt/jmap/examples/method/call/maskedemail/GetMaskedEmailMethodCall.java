package rs.ltt.jmap.examples.method.call.maskedemail;

import rs.ltt.jmap.annotation.JmapMethod;
import rs.ltt.jmap.common.method.call.standard.GetMethodCall;
import rs.ltt.jmap.examples.entity.MaskedEmail;

@JmapMethod("MaskedEmail/get")
public class GetMaskedEmailMethodCall extends GetMethodCall<MaskedEmail> {
  public GetMaskedEmailMethodCall(String accountId) {
    super(accountId, null, null, null);
  }
}
