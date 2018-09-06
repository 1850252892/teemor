package xyz.teemor.custom.custom_provider.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.teemor.custom.custom_provider.entity.User;

public interface UserDao {
//    @Select(" select *\n" +
//            "        from u_user\n" +
//            "        where id=#{uid,jdbcType=INTEGER}")
    User selectUserById(String uid);

    Integer insertUser(User user);
}
