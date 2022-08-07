package simple.mind.mybat.repo;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import simple.mind.mybat.User;

@Mapper
@Repository
public interface UserRepository {

  @Select("SELECT * FROM " + User.TABLENAME + " where id = #{id}")
  User getUserById(Long id);

  @Select("SELECT * FROM " + User.TABLENAME + " where user_id = #{userId}")
  User findByUserId(String userId);

  @Select("SELECT * FROM users")
  List<User> findAll();

  @Insert(User.INSERT_QUERY)
  Long insert(User user);

  @Update(User.UPDATE_QUERY)
  void update(User user);

  @Update(User.UPDATE_PASSWORD_QUERY)
  void updatePassword(User user);

  @Update(User.UPDATE_DEACTIVE_QUERY)
  void updateActivation(int isActive, String userId);

  default void activateUser(String userId) {
    updateActivation(User.Active.ACTIVE, userId);
  }

  default void deactivateUser(String userId) {
    updateActivation(User.Active.INACTIVE, userId);
  }

  int delete(User user);

}
