package com.xiaozhai.community.community.provider;


import com.alibaba.fastjson.JSON;
import com.xiaozhai.community.community.dto.GitUserDTO;
import com.xiaozhai.community.community.dto.TokenacessDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccesstoken(TokenacessDTO tokenacessDTO){
         MediaType mediaType= MediaType.get("application/json; charset=utf-8");
        //创建okhttp客户端
        OkHttpClient client = new OkHttpClient();
        //创建POST请求头,
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(tokenacessDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()){
            //这里动作链最后是string() 不是ToString()
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            //返回token
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GitUserDTO getUser(String accesstoken){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accesstoken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            try {
                String string = response.body().string();
                //将返回json字符串转换成 GitUser 对象
                GitUserDTO gitUserDTO = JSON.parseObject(string, GitUserDTO.class);
                return gitUserDTO;
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
        return null;
    }
}
