package com.spring.mvc.controller;

import com.spring.mvc.service.HelloService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(path = "/hello")
    public void helloWorld(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String name = request.getParameter("name");
        String responseBody = helloService.hello(name);
        response.getWriter().println(responseBody);
    }
}
