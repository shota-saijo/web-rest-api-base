package core.domain.service;

import core.domain.write.model.Auth;
import core.domain.write.model.UserId;

public interface AuthQueryService {

  Auth findByUserId(UserId userId);
}
