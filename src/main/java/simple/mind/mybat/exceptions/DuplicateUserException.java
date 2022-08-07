package simple.mind.mybat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DuplicateUserException extends BaseException {

  private static final long serialVersionUID = -7761668367752325445L;

  public DuplicateUserException(String msg) {
    super(msg);
  }

  @Override
  public String getMessage() {
    return message;
  }

}
