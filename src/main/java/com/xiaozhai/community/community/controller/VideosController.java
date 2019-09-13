package com.xiaozhai.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VideosController {

    @GetMapping("/videos")
    public String tools(){ return "videos" ;}
}
