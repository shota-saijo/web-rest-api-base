package core.domain.repository;

import core.domain.write.model.User;
import org.sql2o.Connection;

public interface UserRepository {

  void insert(User user, Connection conn);

  void update(User user);

}
