package com.xiaozhai.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//将该类标识为bean
@Controller
public class IndexController {

    @GetMapping("/")
    public String indexController(){
        return "index";
    }
}
