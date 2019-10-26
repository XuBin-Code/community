package life.code.xubin.controller;

import life.code.xubin.DTO.Access_TokenDTO;
import life.code.xubin.DTO.GithubUser;
import life.code.xubin.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintStream;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @GetMapping("/callback")
    public  String callback(@RequestParam(name="code")String code,
                            @RequestParam(name="state")String state,
                            HttpServletRequest request)  {

        Access_TokenDTO accessTokenDTO = new Access_TokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken= githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user =githubProvider.getuser(accessToken);
        System.out.println(user.getName());
        if(user !=null){
            request.getSession().setAttribute("user",user);
            return  "redirect:index";
        }
        else{
            return  "redirect:index";
        }

    }
}
