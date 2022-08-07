package simple.mind.mybat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidUserNameException extends BaseException {

  public InvalidUserNameException(String msg) {
    super(msg);
  }

  private static final long serialVersionUID = -3682827444170778988L;

  @Override
  public String getMessage() {
    return message;
  }

}
