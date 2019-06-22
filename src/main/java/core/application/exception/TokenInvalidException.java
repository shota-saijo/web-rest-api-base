package core.application.exception;

public class TokenInvalidException extends BaseRuntimeException {

  public TokenInvalidException(String errorMessage) {
    super(errorMessage);
  }

  public TokenInvalidException(String errorMessage, Throwable e) {
    super(errorMessage, e);
  }
}
