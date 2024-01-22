package mapper;

import entity.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * @author yiren
 * @Description
 * @create 2024/1/19
 */
public interface UserMapper {
    @Select("select * from users where user_name = #{userName} and user_pwd = #{userPwd}")
    public Users selectUserByInfo(@Param("userName") String userName, @Param("userPwd") String userPwd);
    @Select("select * from users")
    public List<Users> queryAllUsers();
    @Insert("insert into users values(#{userAccount},#{userPwd})")
    public boolean registerUser(@Param("userAccount") String userAccount, @Param("userPwd") String userPwd);
}
