package core.domain.repository;

import core.domain.write.model.Auth;
import org.sql2o.Connection;

public interface AuthRepository {

  void insert(Auth auth, Connection conn);
}
