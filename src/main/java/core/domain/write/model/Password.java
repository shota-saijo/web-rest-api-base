package core.domain.write.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password extends StringValueObject {

  private Password(String value) {
    super(value);
  }

  public static Password of(String value) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String encodedPassword = encoder.encode(value);
    return new Password(encodedPassword);
  }
}
