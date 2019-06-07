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
    SU, ADMIN, PUBLIC
  }

  public static User create(String email) {
    UserId userId = UserId.of();
    User user = new User(
        userId,
        email,
        "匿名希望",
        UserRole.PUBLIC
    );
    user.created(userId);
    return user;
  }

  public User changeName(String name) {
    this.userName = name;
    this.updated(this.id);
    return this;
  }
}