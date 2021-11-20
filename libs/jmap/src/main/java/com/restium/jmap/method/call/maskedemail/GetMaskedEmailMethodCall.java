package com.restium.jmap.method.call.maskedemail;

import com.restium.jmap.entity.MaskedEmail;
import rs.ltt.jmap.annotation.JmapMethod;
import rs.ltt.jmap.common.method.call.standard.GetMethodCall;

@JmapMethod("MaskedEmail/get")
public class GetMaskedEmailMethodCall extends GetMethodCall<MaskedEmail> {
  public GetMaskedEmailMethodCall(String accountId) {
    super(accountId, null, null, null);
  }
}
