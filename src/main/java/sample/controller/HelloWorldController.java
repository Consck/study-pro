package sample.controller;

import com.fasterxml.jackson.core.json.async.NonBlockingJsonParser;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sample.application.HelloWordManager;
import sample.config.MqttConfig;
import sample.domain.WorkInfoFormEntity;

import java.sql.Date;
import java.util.List;

/**
 * @author bianjinyue
 * @Description
 * @date 2022-06-22 10:48
 */
@Controller
@RequestMapping("/{id}")
public class HelloWorldController {
    @Autowired
    private HelloWordManager helloWordManager;
    @Autowired
    private MqttClient mqttClient;

    /**
     * 此方法会首先被调用，并将方法结果作为model的属性
     * 再执行具体的Controller方法
     * @param id
     */
    @ModelAttribute
    public void proReq(@PathVariable long id,ModelAndView model){
        System.out.println("先调用此方法");
/*        model.addAttribute("id", id);
        ModelAndView model = new ModelAndView();*/
        model.addObject("id", id);
    }

    @RequestMapping("/c")
    @ResponseBody
    public ModelMap contains(ModelAndView model){
        return model.getModelMap();
    }

    /**
     * ResponseBody 注解：直接返回内容而不是视图名，将返回的对象输出到客户端。如果不是字符串，默认使用Jackson序列化成JSON字符串后输出
     * 若无此注解返回视图名，视图默认保存在 resources/templates 目录下
     * @param name
     * @return
     */
    @RequestMapping(path="/{name}")
    public @ResponseBody String helloWorld(@PathVariable String name, @PathVariable("id")long testId,@RequestBody Object obj){
        return "hello," + name + ",id=" + testId + ",obj=" + obj;
    }


    @RequestMapping("/test")
    public @ResponseBody String helloWorld2(){
        return "hello," + helloWordManager.helloWord();
    }

    /**
     * 声明一个方法来自己扩展绑定的特性
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    @RequestMapping("/date")
    @ResponseBody
    public void printDate(Date d){
        System.out.println(d);
    }


    @RequestMapping("/v")
    @ResponseBody
    public void addWorkInfo(@Validated({WorkInfoFormEntity.Add.class}) WorkInfoFormEntity entity, BindingResult result){
        if(result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            FieldError error = (FieldError) list.get(0);
            System.out.println(error.getObjectName()+","+error.getField()+","+error.getDefaultMessage());
        }
    }

    @RequestMapping("/send")
    public void sendMsg() throws MqttException {
        String publish = "test rmqx send message";
        mqttClient.publish("/device/123", publish.getBytes(), 1, false);
    }
}
