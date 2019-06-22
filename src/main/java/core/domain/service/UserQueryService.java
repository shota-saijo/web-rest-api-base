package core.domain.service;

import core.domain.write.model.User;
import core.domain.write.model.UserId;
import java.util.List;

public interface UserQueryService {

  List<User> findAll();

  User findById(UserId userId);

  User findByEmail(String email);
}
