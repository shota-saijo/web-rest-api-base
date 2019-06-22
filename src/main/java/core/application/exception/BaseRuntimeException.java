package core.application.exception;

public class BaseRuntimeException extends RuntimeException {

  public BaseRuntimeException(String errorMessage) {
    super(errorMessage);
  }

  public BaseRuntimeException(String errorMessage, Throwable e) {
    super(errorMessage, e);
  }
}
