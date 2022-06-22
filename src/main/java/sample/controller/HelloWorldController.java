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

    @RequestMapping("/{name}")
    public @ResponseBody String helloWorld(@RequestParam String name){
        return "hello," + name;
    }


    @RequestMapping("/test")
    public @ResponseBody String helloWorld2(){
        return "hello," + helloWordManager.helloWord();
    }
}
