package com.xiaozhai.community.community.controller;

import com.xiaozhai.community.community.dto.GitUserDTO;
import com.xiaozhai.community.community.dto.TokenacessDTO;
import com.xiaozhai.community.community.mapper.UserMapper;
import com.xiaozhai.community.community.model.User;
import com.xiaozhai.community.community.provider.GithubProvider;
import com.xiaozhai.community.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Slf4j
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserService userService;




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
            HttpServletRequest request,
            HttpServletResponse response){
        TokenacessDTO tokenacessDTO = new TokenacessDTO();
        tokenacessDTO.setClient_id(clientId);
        tokenacessDTO.setClient_secret(clinentSecret);
        tokenacessDTO.setCode(code);
        tokenacessDTO.setState(state);
        tokenacessDTO.setRedirect_uri(redirectUrl);
        String accesstoken = githubProvider.getAccesstoken(tokenacessDTO);
        GitUserDTO gitUserDTO = githubProvider.getUser(accesstoken);
        if(gitUserDTO != null){
            User user = new User();
            String token =UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccountId(String.valueOf(gitUserDTO.getId()));
            user.setName(gitUserDTO.getLogin());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(gitUserDTO.getAvatar_url());
            userService.insertOrUpdate(user);
//           为用户写入token，记录登录过
            response.addCookie(new Cookie("token",token));
            //获取session写入user键值对
            //重定向到主页
            return "redirect:/";
        }else{
            log.error("get git token {}",gitUserDTO);
            //登录失败
            return "redirect:/";
        }

    }

    @GetMapping("/loginOut")
    public String loginOut(HttpServletRequest request,
                           HttpServletResponse response){
//      删除用户登录态
        request.getSession().removeAttribute("user");
//        设置Cookie的token为NUll实现的登出
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "index";
    }
}
