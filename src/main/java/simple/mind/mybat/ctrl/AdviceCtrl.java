package simple.mind.mybat.ctrl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import simple.mind.mybat.exceptions.BaseException;
import simple.mind.mybat.exceptions.DuplicateUserException;
import simple.mind.mybat.exceptions.InvalidPasswordException;
import simple.mind.mybat.exceptions.InvalidUserIdException;
import simple.mind.mybat.exceptions.InvalidUserNameException;

@ControllerAdvice
public class AdviceCtrl {
  @AllArgsConstructor
  @Data
  public class Response {
    String status;
    String msg;
  }

  @ExceptionHandler({ InvalidPasswordException.class, //
      InvalidUserIdException.class, //
      InvalidUserNameException.class, //
      DuplicateUserException.class })
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public @ResponseBody Response handleException(BaseException e) {
    String msg = e.getMessage();
    return new Response("failed", msg);
  }
}
