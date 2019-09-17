package com.xiaozhai.community.community.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(CustomizedException.class)
    String handleControllerException(HttpServletRequest request,
                                                Throwable ex,
                                                Model model) {
        if (ex instanceof CustomizedException){
            model.addAttribute("message",ex.getMessage());
        }else{
            model.addAttribute("message","服务器冒烟喽");
        }
        return "error";
    }
}