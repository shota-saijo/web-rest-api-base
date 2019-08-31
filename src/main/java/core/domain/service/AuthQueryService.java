package core.domain.service;

import core.domain.read.model.Auth;

public interface AuthQueryService {

  Auth findByEmail(String email);
}
