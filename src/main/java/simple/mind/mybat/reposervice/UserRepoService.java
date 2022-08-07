package simple.mind.mybat.reposervice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import simple.mind.mybat.User;
import simple.mind.mybat.exceptions.DuplicateUserException;
import simple.mind.mybat.exceptions.InvalidPasswordException;
import simple.mind.mybat.exceptions.InvalidUserNameException;
import simple.mind.mybat.funcs.CommonFuncServices;
import simple.mind.mybat.repo.UserRepository;

@Service
public class UserRepoService implements RepoService<UserRepository> {
  public static final String specialChars = "!@#$%&*-/+:;";
  private static int NAME_LENGTH = 255;
  private static int SALT_LENGTH = 10;
  @Autowired
  private CommonFuncServices common;
  @Autowired
  private UserRepository repo;

  private boolean validateName(String s) {
    s = s.trim();
    if (!s.matches("^[a-zA-Z ]{2," + NAME_LENGTH + "}"))
      throw new InvalidUserNameException(
          "Name cannot have anything other than leters and spaces, expected minimum length 2");
    return true;
  }

  private User getPassword(User user) {
    String s = user.getPassword();
    final String exceptionmsg = "Password have to be more than or equal 8 char long."
        + " Have to contain at least on lower case and one upper chase one number and one speical character from thef following list "
        + specialChars;
    if (s.length() < 8)
      throw new InvalidPasswordException(exceptionmsg);
    boolean cSmall = false, cUpper = false, cNumber = false, cSpecial = false;
    for (int i = 0; i < s.length(); i++) {
      char p = s.charAt(i);
      if (p > 96 && p < 123) {
        cSmall = true;
      } else if (p > 64 && p < 91) {
        cUpper = true;
      } else if (p > 47 && p < 58) {
        cNumber = true;
      } else if (specialChars.contains(p + "")) {
        cSpecial = true;
      }
    }
    if (!(cSmall && cUpper && cNumber && cSpecial))
      throw new InvalidPasswordException(exceptionmsg);
    user.setSalt(common.getRandomString(SALT_LENGTH - 3));
    user.setPassword(s);
    user.setPassword(common.getSha512Password(user.getPassword(), user.getSalt()));
    return user;
  }

  public boolean register(User user) {
    if (repo.findByUserId(user.getUserId()) != null) {
      throw new DuplicateUserException("User Id: " + user.getUserId() + " is not available.");
    }
    validateName(user.getFirstName());
    validateName(user.getLastName());
    user = getPassword(user);
    Long l = repo.insert(user);
    user.setAutoId(l);
    return true;
  }

  public String validateLogin(String userId, String password) {
    User user = repo.findByUserId(userId);
    String encp = common.getSha512Password(password, user.getSalt());
    if (user.getPassword().compareTo(encp) != 0) {
      user = null;
    }
    return safeData(user);
  }

  private String safeData(User user) {
    Gson g = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create();
    JsonObject obj = g.toJsonTree(user).getAsJsonObject();
    obj.remove("auto-id");
    obj.remove("password");
    obj.remove("salt");
    obj.remove("is-active");
    obj.remove("removed-by");
    obj.remove("registration-time");
    obj.remove("update-time");
    return toString();
  }

  @Override
  public UserRepository $() {
    return repo;
  }
}
