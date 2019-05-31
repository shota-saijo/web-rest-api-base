package core.domain.write.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User extends AbstractEntity<UserId> {

  private UserId id;

  private String email;

  private String userName;

  private UserRole userRole;

  public enum UserRole {
    SU, ADMIN, PUBLIC;
  }
}