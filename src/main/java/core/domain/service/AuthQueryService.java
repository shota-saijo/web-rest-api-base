package core.domain.service;

public interface AuthQueryService {

  Auth findByEmail(String email);
}
