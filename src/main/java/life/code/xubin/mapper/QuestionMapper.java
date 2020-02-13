package life.code.xubin.mapper;

import life.code.xubin.DTO.QuestionDTO;
import life.code.xubin.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmtModified,gmtCreate,creator,tag) values (#{title},#{description},#{gmt_modified},#{gmt_create},#{creator},#{tag})")
    public void create(Question question);
    @Select("select * from question limit #{offset},#{pageSize}")
    List<Question> list(@Param(value = "offset") int offset,@Param(value = "pageSize") int pageSize);
    @Select("select count(1) from question where creator=#{id}")
    Integer countByUser(Integer id);
    @Select("select count(1) from question")
    Integer count();
    @Select("select * from question where creator=#{id} limit #{offset},#{pageSize}")
    List<Question> listByUser(@Param(value = "offset") int offset,@Param(value = "pageSize") int pageSize,@Param(value = "id") int id);
    @Select("select * from question where id=#{id}")
    Question findById(@Param(value = "id") int id);
    @Update("update question set title=#{title},description=#{description},gmt_modified=#{gmtModified},creator=#{creator},tag=#{tag} where id=#{id}")
    public void updateQuestion(Question question);
}
