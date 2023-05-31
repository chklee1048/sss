package com.itwill.spring2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {
    @GetMapping("/test")
    public String home() {
        
        log.info("test");
        
        return "test";
    }
}
