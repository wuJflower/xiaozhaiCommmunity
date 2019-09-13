package com.xiaozhai.community.community.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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