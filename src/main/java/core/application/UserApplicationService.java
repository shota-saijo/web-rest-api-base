package core.application;

import com.google.inject.Inject;
import core.domain.service.UserQueryService;
import core.domain.write.model.User;
import java.util.List;

public class UserApplicationService {

  private UserQueryService userQueryService;

  @Inject
  public UserApplicationService(UserQueryService userQueryService) {
    this.userQueryService = userQueryService;
  }

  public List<User> getUsers() {
    return userQueryService.findAll();
  }
}
