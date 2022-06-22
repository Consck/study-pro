package sample.application;

import org.springframework.stereotype.Service;

/**
 * @author bianjinyue
 * @Description
 * @date 2022-06-22 10:56
 */
@Service
public class HelloWordManager {

    public String helloWord(){
        return "test manager";
    }
}
