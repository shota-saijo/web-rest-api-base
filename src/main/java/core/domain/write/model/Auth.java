package core.domain.write.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class Auth extends AbstractEntity<UserId> {

  private UserId id;

  private Password password;

  public static Auth create(String userId, String password) {
    Auth auth = new Auth(UserId.of(userId), Password.of(password));
    auth.created(UserId.of(userId));
    return auth;
  }
}
