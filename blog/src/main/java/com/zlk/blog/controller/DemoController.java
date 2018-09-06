package com.zlk.blog.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class DemoController {

    @RequestMapping("/demo")
    public String demo(){
        System.out.println("13");
        return "/WEB-INF/demo.jsp";
    }
    @RequestMapping("/demo2")
    public String demo2(){

        return "demo2";
    }
    @PostMapping("/upload")
    public @ResponseBody String index(@RequestParam("data") String data){
        System.out.println("接收："+data);
        return data;
    }
}
