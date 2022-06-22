package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sample.application.HelloWordManager;

/**
 * @author bianjinyue
 * @Description
 * @date 2022-06-22 10:48
 */
@Controller
public class HelloWorldController {
    @Autowired
    private HelloWordManager helloWordManager;

    /**
     * ResponseBody 注解：直接返回内容而不是视图名，将返回的对象输出到客户端。如果不是字符串，默认使用Jackson序列化成JSON字符串后输出
     * 若无此注解返回视图名，视图默认保存在 resources/templates 目录下
     * @param name
     * @return
     */
    @RequestMapping("/{name}")
    public @ResponseBody String helloWorld(@RequestParam String name){
        return "hello," + name;
    }


    @RequestMapping("/test")
    public @ResponseBody String helloWorld2(){
        return "hello," + helloWordManager.helloWord();
    }
}
