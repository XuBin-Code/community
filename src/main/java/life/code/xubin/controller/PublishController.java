package life.code.xubin.controller;

import life.code.xubin.DTO.QuestionDTO;
import life.code.xubin.mapper.QuestionMapper;
import life.code.xubin.model.Question;
import life.code.xubin.model.User;
import life.code.xubin.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value="title", required = false)String title,
            @RequestParam(value="description", required = false)String description,
            @RequestParam(value="tag", required = false)String tag,
            @RequestParam(value="id", required = false)Integer id,
            HttpServletRequest request, Model model){
        User user= (User) request.getSession().getAttribute("user");
        if(user==null) {
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        Question question =new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,Model model,HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        QuestionDTO question=questionService.findById(id);
        User user1=question.getUser();
        if(user1.getAccountId().equals(user.getAccountId())){
          model.addAttribute("title",question.getTitle());
          model.addAttribute("description",question.getDescription());
          model.addAttribute("tag",question.getTag());
          model.addAttribute("id",question.getId());
          return "publish";
        }else {
            return "redirect:/";
        }


    }
}
