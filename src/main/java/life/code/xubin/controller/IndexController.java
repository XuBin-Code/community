package life.code.xubin.controller;

import com.github.pagehelper.PageInfo;
import life.code.xubin.DTO.PaginationDTO;
import life.code.xubin.DTO.QuestionDTO;
import life.code.xubin.mapper.UserMapper;
import life.code.xubin.model.User;
import life.code.xubin.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller

public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(@RequestParam(value="pageNum",defaultValue="1")int pageNum,
                        @RequestParam(value="pageSize",defaultValue="2")int pageSize,
                        Model model,HttpServletRequest request){




        PaginationDTO paginationDTO =questionService.list(pageNum,pageSize);

        model.addAttribute("pagination",paginationDTO);
        return "index";
    }
}
