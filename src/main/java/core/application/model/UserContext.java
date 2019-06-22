package core.application.model;

import core.domain.write.model.User.UserRole;
import core.domain.write.model.UserId;
import lombok.Value;

@Value
public class UserContext {

  private UserId userId;

  private UserRole userRole;

}
