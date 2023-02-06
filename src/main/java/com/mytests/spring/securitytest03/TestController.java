package com.mytests.spring.securitytest03;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * this controller is for configuration
 * com.mytests.spring.securitytest03.SecurityConfig.TestWebSecurityConfigurerAdapter
 */
@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("/test1")
    public String test1() {
        return "test1";
    }

    @GetMapping("/test1/foo")
    public String test11() {
        return "test1/foo";
    }

    @GetMapping("/test2")
    public String test2() {
        return "test2";
    }

    @GetMapping("/test3")
    public String test3() {
        return "test3";
    }
    @GetMapping("/test3/bar")
    public String test31() {
        return "test3/bar";
    }
}
