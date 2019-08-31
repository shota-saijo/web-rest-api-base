package core.application;

import com.google.inject.Inject;
import core.application.command.UserCommand.ChangeEmailCommand;
import core.application.command.UserCommand.ChangeNameCommand;
import core.application.command.UserCommand.CreateCommand;
import core.domain.repository.UserRepository;
import core.domain.service.UserCreateFacadeService;
import core.domain.service.UserQueryService;
import core.domain.write.model.Auth;
import core.domain.write.model.User;
import core.domain.write.model.UserId;
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

  public void changeName(String id, ChangeNameCommand command) {
    UserId userId = UserId.of(id);
    User user = userQueryService.findById(userId);
    user.changeName(command.getUserName(), userId);
    userRepository.update(user);
  }

  public void changeEmail(String id, ChangeEmailCommand command) {
    UserId userId = UserId.of(id);
    User user = userQueryService.findById(userId);
    user.changeEmail(command.getEmail(), userId);
    userRepository.update(user);
  }
}
