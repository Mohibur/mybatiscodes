package simple.mind.mybat.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import simple.mind.mybat.User;
import simple.mind.mybat.funcs.CommonFuncServices;
import simple.mind.mybat.reposervice.UserRepoService;

@RestController
public class UserCtrl {

  @Autowired
  CommonFuncServices common;
  @Autowired
  private UserRepoService repo;

  @PostMapping("/user/insert")
  public User getUser(@RequestBody String req) {
    Gson g = new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create();
    User user = g.fromJson(req, User.class);
    repo.register(user);
    return user;
  }

  @PostMapping("/user/login")
  public String login(@RequestBody String req) {
    JsonObject userObj = JsonParser.parseString(req).getAsJsonObject();
    return repo.validateLogin(userObj.get("user-id").getAsString(), userObj.get("password").getAsString());
  }

  @GetMapping("/user/{id}")
  public User getUser(@PathVariable Long id) {
    return repo.$().getUserById(id);
  }

}
