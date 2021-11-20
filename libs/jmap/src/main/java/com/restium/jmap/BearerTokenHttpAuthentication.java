package com.restium.jmap;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import okhttp3.Request.Builder;
import org.jetbrains.annotations.NotNull;
import rs.ltt.jmap.client.http.HttpAuthentication;

@AllArgsConstructor
public final class BearerTokenHttpAuthentication implements HttpAuthentication {
  @NonNull
  private final String username;
  @NonNull
  private final String token;

  public void authenticate(Builder builder) {
    builder.header("Authorization", "Bearer " + this.token);
  }

  @NotNull
  public String getUsername() {
    return this.username;
  }
}
