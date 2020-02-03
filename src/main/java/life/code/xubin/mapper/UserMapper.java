package life.code.xubin.mapper;

import life.code.xubin.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_modified,gmt_create,avatar_url) values (#{name},#{accountId},#{token},#{gmtModified},#{gmtCreate},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findbytoken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findbyid(@Param("id") Integer id);
}
