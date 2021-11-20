package com.restium.jmap.method.response.maskedemail;

import com.restium.jmap.entity.SetMaskedEmail;
import rs.ltt.jmap.annotation.JmapMethod;
import rs.ltt.jmap.common.entity.SetError;
import rs.ltt.jmap.common.method.response.standard.SetMethodResponse;

import java.util.Map;

@JmapMethod("MaskedEmail/set")
public class SetMaskedEmailMethodResponse extends SetMethodResponse<SetMaskedEmail> {
  public SetMaskedEmailMethodResponse(String accountId, String oldState, String newState, Map<String, SetMaskedEmail> created, Map<String, SetMaskedEmail> updated, String[] destroyed, Map<String, SetError> notCreated, Map<String, SetError> notUpdated, Map<String, SetError> notDestroyed) {
    super(accountId, oldState, newState, created, updated, destroyed, notCreated, notUpdated, notDestroyed);
  }
}
