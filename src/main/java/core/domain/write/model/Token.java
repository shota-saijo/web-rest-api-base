package core.domain.write.model;

import core.application.exception.TokenInvalidException;
import java.util.Objects;

public class Token extends StringValueObject {

  public Token(String value) {
    super(value);
  }

  public static Token ofByHeader(String value) {
    if (Objects.isNull(value)) {
      throw new TokenInvalidException("token is not null");
    }
    if (!value.startsWith("Bearer ")) {
      throw new TokenInvalidException("token must start with 'Bearer '");
    }
    return new Token(value.replace("Bearer ", ""));
  }
}
