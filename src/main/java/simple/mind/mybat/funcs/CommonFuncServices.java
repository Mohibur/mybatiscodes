package simple.mind.mybat.funcs;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class CommonFuncServices {
  /*
   * byte array get encoded to base64 but get stored and processed as given string
   */
  public String getRandomString(int length) {
    byte[] array = new byte[length];
    new Random().nextBytes(array);
    return Base64.getEncoder().encodeToString(array);
  }

  public String getSha512Password(String password, String slat) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      md.update(slat.getBytes(StandardCharsets.UTF_8));
      byte[] b = md.digest(password.getBytes());
      return Base64.getEncoder().encodeToString(b);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }
}
