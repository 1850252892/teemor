package com.zlk.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView ;

@Controller
public class
    UrlController {

    @GetMapping("/admin/editType")
    public ModelAndView urlEditType(){
        return new ModelAndView( "admin/editType");
    }

    @GetMapping("/admin/createEssay")
    public ModelAndView urlCreateEssay(){
        return new ModelAndView("admin/createEssay");
    }

    @RequestMapping("/admin/console")
    public ModelAndView urlConsole(){
        return new ModelAndView("admin/console");
    }

    /**
     * 文章内容
     * @return
     */
    @RequestMapping("/**/p/**")
    public ModelAndView urlEssayData(){
        return new ModelAndView("essay/essayData");
    }

    /**
     * 分组
     * @return
     */
    @GetMapping("/**/category/**")
    public  ModelAndView urlEssayCategory(){return new ModelAndView("essay/category");}

    /**
     * 归档
     * @return
     */
    @GetMapping("/**/archives/**")
    public  ModelAndView urlEssayForDate(){return new ModelAndView("essay/date");}

    /**
     * 请求登陆页面
     * @return
     */
    @GetMapping ("/login_page")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    /**
     * 用户主页
     * @return
     */
    @GetMapping("/")
    public ModelAndView  author(){return new ModelAndView ("author");}

    @GetMapping("/u/*")
    public ModelAndView blog(){return new ModelAndView("blog");}

    @GetMapping("/article/**")
    public String article(){
        return "lw-article";
    }
}
