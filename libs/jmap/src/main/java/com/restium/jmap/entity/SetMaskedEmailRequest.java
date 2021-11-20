package com.restium.jmap.entity;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rs.ltt.jmap.common.entity.AbstractIdentifiableEntity;

@EqualsAndHashCode(callSuper = false)
@Value
public class SetMaskedEmailRequest extends AbstractIdentifiableEntity {
  @NonNull
  String state;
  @NonNull
  String description;
  @Nullable
  String url;
  @NotNull
  String emailPrefix;
}
