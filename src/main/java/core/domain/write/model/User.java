package core.domain.write.model;

import io.javalin.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class User extends AbstractEntity<UserId> {

  private UserId id;

  private String email;

  private String userName;

  private UserRole userRole;

  public enum UserRole implements Role {
    SU(3), ADMIN(2), PUBLIC(1);

    private int level;

    UserRole(int level) {
      this.level = level;
    }

    public int getLevel() {
      return level;
    }
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