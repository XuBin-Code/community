package life.code.xubin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import life.code.xubin.DTO.PaginationDTO;
import life.code.xubin.DTO.QuestionDTO;
import life.code.xubin.mapper.QuestionMapper;
import life.code.xubin.mapper.UserMapper;
import life.code.xubin.model.Question;
import life.code.xubin.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO list(int pageNum, int pageSize) {
        PaginationDTO paginationDTO = new PaginationDTO();
        int totalCount=questionMapper.count();
        paginationDTO.setpagination(totalCount,pageNum,pageSize);
        if(pageNum<1){
            pageNum=1;
        }
        if(pageNum>paginationDTO.getTotalPage()){
            pageNum=paginationDTO.getTotalPage();
        }
        int offset = pageSize * (pageNum - 1);
        List<Question> questions = questionMapper.list(offset,pageSize);
        List<QuestionDTO> questionDTOList =new ArrayList<>();
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
          User user = userMapper.findbyid(question.getCreator());
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
            paginationDTO.setQuestions(questionDTOList);
        }







        return paginationDTO;
    }
}
