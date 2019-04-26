package com.xiaozhai.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//将该类标识为bean
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name, Model model){
        //将浏览器传入的参数,加入到上下文,
        model.addAttribute("name", name);
        return "hello";
    }
}
