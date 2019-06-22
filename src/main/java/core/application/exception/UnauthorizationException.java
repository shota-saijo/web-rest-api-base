package core.application.exception;

public class UnauthorizationException extends BaseRuntimeException {

  public UnauthorizationException(String errorMessage) {
    super(errorMessage);
  }

  public UnauthorizationException(String errorMessage, Throwable e) {
    super(errorMessage, e);
  }
}
