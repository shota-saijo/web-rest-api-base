package core.domain.write.model;

import java.util.UUID;

public class UserId extends StringId {

  public UserId(String value) {
    super(value);
  }

  public static UserId of(String value) {
    return new UserId(value);
  }

  public static UserId of() {
    return new UserId(UUID.randomUUID().toString());
  }
}
