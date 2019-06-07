package core.application;

import com.google.inject.Inject;
import core.domain.repository.UserRepository;
import core.domain.service.UserQueryService;
import core.domain.write.model.User;
import core.domain.write.model.UserId;
import core.ui.controller.UserController;
import core.ui.controller.UserController.UserCreateCommand;
import java.util.List;

public class UserApplicationService {

  private UserQueryService userQueryService;

  private UserRepository userRepository;

  @Inject
  public UserApplicationService(UserQueryService userQueryService, UserRepository userRepository) {
    this.userQueryService = userQueryService;
    this.userRepository = userRepository;
  }

  public List<User> getUsers() {
    return userQueryService.findAll();
  }

  public UserId createUser(UserCreateCommand command) {
    User user = User.create(command.getEmail());
    return userRepository.insert(user);
  }
}
