package com.restium.jmap.method.call.maskedemail;

import com.restium.jmap.entity.CreateMaskedEmailRequest;
import lombok.NonNull;
import rs.ltt.jmap.annotation.JmapMethod;
import rs.ltt.jmap.common.Request;
import rs.ltt.jmap.common.method.call.standard.SetMethodCall;

import java.util.Map;

@JmapMethod("MaskedEmail/set")
public class SetMaskedEmailMethodCall extends SetMethodCall<CreateMaskedEmailRequest> {
  public SetMaskedEmailMethodCall(@NonNull String accountId, String ifInState, Map<String, CreateMaskedEmailRequest> create, Map<String, Map<String, Object>> update, String[] destroy, Request.Invocation.ResultReference destroyReference) {
    super(accountId, ifInState, create, update, destroy, destroyReference);
  }
}
