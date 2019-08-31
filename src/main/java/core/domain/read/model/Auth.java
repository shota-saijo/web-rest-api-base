package core.domain.read.model;

import lombok.Value;

@Value
public class Auth {
  private String userId;
  private String password;
}
