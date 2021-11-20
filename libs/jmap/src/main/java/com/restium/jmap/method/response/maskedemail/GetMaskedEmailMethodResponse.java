package com.restium.jmap.method.response.maskedemail;

import com.restium.jmap.entity.MaskedEmail;
import rs.ltt.jmap.annotation.JmapMethod;
import rs.ltt.jmap.common.method.response.standard.GetMethodResponse;

@JmapMethod("MaskedEmail/get")
public class GetMaskedEmailMethodResponse extends GetMethodResponse<MaskedEmail> {
  public GetMaskedEmailMethodResponse(String accountId, String state, String[] notFound, MaskedEmail[] list) {
    super(accountId, state, notFound, list);
  }
}
