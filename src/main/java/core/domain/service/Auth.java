package core.domain.service;

import lombok.Value;

@Value
public class Auth {
  private String userId;
  private String password;
}
