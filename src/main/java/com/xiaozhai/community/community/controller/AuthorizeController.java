package com.xiaozhai.community.community.controller;

import com.xiaozhai.community.community.dto.GitUserDTO;
import com.xiaozhai.community.community.dto.TakenacessDTO;
import com.xiaozhai.community.community.mapper.UserMapper;
import com.xiaozhai.community.community.model.User;
import com.xiaozhai.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserMapper userMapper;


    //注入application.properties 里的值
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clinentSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;

    @GetMapping("/callback")
    public String callback(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "state") String state,
            HttpServletRequest request){
        TakenacessDTO takenacessDTO = new TakenacessDTO();
        takenacessDTO.setClient_id(clientId);
        takenacessDTO.setClient_secret(clinentSecret);
        takenacessDTO.setCode(code);
        takenacessDTO.setState(state);
        takenacessDTO.setRedirect_uri(redirectUrl);
        String accesstoken = githubProvider.getAccesstoken(takenacessDTO);
        GitUserDTO gitUserDTO = githubProvider.getUser(accesstoken);
        if(gitUserDTO != null){
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setAccountID(String.valueOf(gitUserDTO.getId()));
            user.setName(gitUserDTO.getLogin());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertUser(user);
            //登录成功,写cookie和session
            request.getSession().setAttribute("user",gitUserDTO);
            //重定向到主页
            return "redirect:/";
        }else{
            //登录失败
            return "redirect:/";
        }
    }
}
