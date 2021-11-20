package rs.ltt.jmap.examples.entity;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rs.ltt.jmap.common.entity.AbstractIdentifiableEntity;

@EqualsAndHashCode(callSuper = false)
@Value
public class CreatedMaskedEmail extends AbstractIdentifiableEntity {
  @NonNull
  String email;
  @NonNull
  String createdBy;
  @NonNull
  String createdAt;
  @Nullable
  String url;
  @NotNull
  String forDomain;
}
