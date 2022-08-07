package simple.mind.mybat.exceptions;

public abstract class BaseException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  protected String message;
  
  protected BaseException(String msg) {
    message = msg;
  }
  
  public abstract String getMessage();
}
