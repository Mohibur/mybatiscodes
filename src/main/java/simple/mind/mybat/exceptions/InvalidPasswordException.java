package simple.mind.mybat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidPasswordException extends BaseException {
  private static final long serialVersionUID = 1580278696066730561L;


  public InvalidPasswordException(String msg) {
    super(msg);
  }

  @Override
  public String getMessage() {
    return message;
  }

}
