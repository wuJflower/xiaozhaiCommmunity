package com.xiaozhai.community.community.exception;

import com.alibaba.fastjson.JSON;
import com.xiaozhai.community.community.dto.ResultDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(CustomizedException.class)
    String handleControllerException(HttpServletRequest request,
                                     HttpServletResponse response,
                                     Throwable ex,
                                     Model model) {

        String contentType = request.getContentType();
        //响应json请求的异常,并返回json
        if ("application/json".equals(contentType)) {
                 ResultDTO resultDTO = null;

                if (ex instanceof CustomizedException){
                    resultDTO= new ResultDTO().errorOf((CustomizedException)ex);
                }

            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  null;
        }else {
//        返回错误页面
            if (ex instanceof CustomizedException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return "error";
        }
    }
}