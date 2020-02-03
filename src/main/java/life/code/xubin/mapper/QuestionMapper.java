package life.code.xubin.mapper;

import life.code.xubin.DTO.QuestionDTO;
import life.code.xubin.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmtModified,gmtCreate,creator,tag) values (#{title},#{description},#{gmt_modified},#{gmt_create},#{creator},#{tag})")
    public void create(Question question);
    @Select("select * from question limit #{offset},#{pageSize}")
    List<Question> list(@Param(value = "offset") int offset,@Param(value = "pageSize") int pageSize);
    @Select("select count(1) from question")
    Integer count();

}
