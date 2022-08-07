package simple.mind.mybat.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexCtrl {
  @GetMapping("/")
  public String ret() {
    return "{\"res\": \"OK\"}";
  }
}
