package core.application;

import com.google.inject.Inject;
import core.domain.repository.UserRepository;
import core.domain.service.UserCreateFacadeService;
import core.domain.service.UserQueryService;
import core.domain.write.model.Auth;
import core.domain.write.model.User;
import core.domain.write.model.UserId;
import core.ui.controller.UserController.CreateCommand;
import java.util.List;

public class UserApplicationService {

  private UserQueryService userQueryService;

  private UserRepository userRepository;

  private UserCreateFacadeService userCreateFacadeService;

  @Inject
  public UserApplicationService(UserQueryService userQueryService, UserRepository userRepository, UserCreateFacadeService userCreateFacadeService) {
    this.userQueryService = userQueryService;
    this.userRepository = userRepository;
    this.userCreateFacadeService = userCreateFacadeService;
  }

  public List<User> getUsers() {
    return userQueryService.findAll();
  }

  public User getUser() {
    return null;
  }

  public UserId createUser(CreateCommand command) {
    User user = User.create(command.getEmail());
    Auth auth = Auth.create(user.getId().toString(), command.getPassword());
    return userCreateFacadeService.createUser(user, auth);
  }

  public void changeName() {
  }

  public void changeEmail() {
  }

  public void delete() {

  }
}
