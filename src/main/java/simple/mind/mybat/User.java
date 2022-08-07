package simple.mind.mybat;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
  public class Active {
    public static final Integer ACTIVE = 1;
    public static final Integer INACTIVE = 0;
  }

  public final static String TABLENAME = "users";
  public final static String INSERT_QUERY = "INSERT INTO " + TABLENAME
      + "(`user_id`, `password`, salt, first_name, last_name) "
      + "VALUE(#{userId}, #{password}, #{salt}, #{firstName}, #{lastName})";
  public final static String UPDATE_QUERY = "UPDATE " + TABLENAME
      + " SET `password` = #{password}, salt = #{salt} WHERE user_id = #{userId}";
  public final static String UPDATE_PASSWORD_QUERY = "UPDATE " + TABLENAME
      + " SET first_name = #{firstName}, last_name = #{lastName} WHERE user_id = #{userId}";
  public final static String UPDATE_DEACTIVE_QUERY = "UPDATE " + TABLENAME
      + " SET is_active = #{isActive} WHERE user_id = #{userId}";

  private Long autoId;
  private String userId;
  private String password;
  private String salt;
  private String firstName;
  private String lastName;
  private Integer isActive;
  private String removedBy;
  private Date registrationTime;
  private Date updateTime;

}
