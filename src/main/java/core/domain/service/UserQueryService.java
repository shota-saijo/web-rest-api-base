package core.domain.service;

import core.domain.write.model.User;
import java.util.List;

public interface UserQueryService {

  List<User> findAll();
}
