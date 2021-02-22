package cn.stians.vblogadmin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String Hello(){
        return "hello";
    }

    @GetMapping("/sys/basic")
    public String Test(){
        return "/sys/basic/**";
    }
}


