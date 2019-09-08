package life.code.xubin.provider;

import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import life.code.xubin.DTO.Access_TokenDTO;
import life.code.xubin.DTO.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(Access_TokenDTO access_tokenDTO)  { MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(access_tokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token= string.split("&")[0].split("=")[1];
           return token;
        }catch (Exception e){
        e.printStackTrace();
        }

        return null;
        }
    public GithubUser getuser(String access_token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + access_token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string =response.body().string();
           GithubUser githubUser= JSON.parseObject(string,GithubUser.class);
           return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
return null;
    }


    }

