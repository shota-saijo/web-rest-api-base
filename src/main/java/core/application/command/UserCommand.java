package core.application.command;

import lombok.Getter;

public interface UserCommand {

  @Getter
  class CreateCommand {
    private String email;
    private String password;
  }

  @Getter
  class ChangeNameCommand {
    private String userName;
  }

  @Getter
  class ChangeEmailCommand {
    private String email;
  }

}
