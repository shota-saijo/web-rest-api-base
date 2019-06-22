package core.domain.service;

import core.domain.write.model.Auth;
import core.domain.write.model.User;
import core.domain.write.model.UserId;

public interface UserCreateFacadeService {

  UserId createUser(User user, Auth auth);

}
