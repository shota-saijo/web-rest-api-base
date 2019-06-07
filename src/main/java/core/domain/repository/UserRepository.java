package core.domain.repository;

import core.domain.write.model.User;
import core.domain.write.model.UserId;

public interface UserRepository {

  UserId insert(User user);
}
