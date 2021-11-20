package com.restium.jmap.method.response.maskedemail;

import com.restium.jmap.entity.CreatedMaskedEmail;
import rs.ltt.jmap.annotation.JmapMethod;
import rs.ltt.jmap.common.entity.SetError;
import rs.ltt.jmap.common.method.response.standard.SetMethodResponse;

import java.util.Map;

@JmapMethod("MaskedEmail/set")
public class CreateMaskedEmailMethodResponse extends SetMethodResponse<CreatedMaskedEmail> {
  public CreateMaskedEmailMethodResponse(String accountId, String oldState, String newState, Map<String, CreatedMaskedEmail> created, Map<String, CreatedMaskedEmail> updated, String[] destroyed, Map<String, SetError> notCreated, Map<String, SetError> notUpdated, Map<String, SetError> notDestroyed) {
    super(accountId, oldState, newState, created, updated, destroyed, notCreated, notUpdated, notDestroyed);
  }
}
