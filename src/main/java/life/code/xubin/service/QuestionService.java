package life.code.xubin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import life.code.xubin.DTO.PaginationDTO;
import life.code.xubin.DTO.QuestionDTO;
import life.code.xubin.mapper.QuestionMapper;
import life.code.xubin.mapper.UserMapper;
import life.code.xubin.model.Question;
import life.code.xubin.model.User;
import org.apache.ibatis.annotations.Insert;
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
        int totalPage;
        if(totalCount%pageSize==0){
            totalPage=totalCount/pageSize;
        }else {
            totalPage=totalCount/pageSize+1;
        }
        if(pageNum<1){
            pageNum=1;
        }
        if(pageNum>totalPage){
            pageNum=totalPage;
        }
        paginationDTO.setpagination(pageNum,totalPage);
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

    public PaginationDTO listByUser(int pageNum, int pageSize, Integer id) {
        PaginationDTO paginationDTO = new PaginationDTO();
        int totalCount = questionMapper.countByUser(id);
        int totalPage;
        if(totalCount%pageSize==0){
            totalPage=totalCount/pageSize;
        }else {
            totalPage=totalCount/pageSize+1;
        }
        if(pageNum<1){
            pageNum=1;
        }
        if(pageNum>totalPage){
            pageNum=totalPage;
        }
        paginationDTO.setpagination(pageNum,totalPage);
        int offset = pageSize * (pageNum - 1);
        List<Question> questions = questionMapper.listByUser(offset,pageSize,id);
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

    public QuestionDTO findById(Integer id) {
        Question questions = questionMapper.findById(id);


        QuestionDTO questionDTO = new QuestionDTO();
        User user = userMapper.findbyid(questions.getCreator());
        BeanUtils.copyProperties(questions,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;


    }

    public void createOrUpdate(Question question) {
        Question questions = questionMapper.findById(question.getId());
        if(questions!=null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.updateQuestion(question);

        }else {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
    }
    }

}
