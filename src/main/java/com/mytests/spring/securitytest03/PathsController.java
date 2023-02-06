package com.mytests.spring.securitytest03;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * this controller is for configuration
 * com.mytests.spring.securitytest03.SecurityConfig.PathsWebSecurityConfigurerAdapter
 */
@Controller
public class PathsController {

    @RequestMapping("/")
    public String home(ModelMap model) {
        model.addAttribute("home_attr1", "home_attr1");
        return "home";
    }
    @RequestMapping({"/path2"})
    public String path1(ModelMap model) {
        model.addAttribute("t1", "regexMatchers(\"/path2.*\")");
        return "t1";
    }

    @GetMapping("/path3/{name}")
    public String path3(ModelMap model, @PathVariable String name) {
        model.addAttribute("path3_name", ".antMatchers(HttpMethod.GET, \"/path3/**\") "+name);
        return "path3";
    }

    @RequestMapping("/secure")
    public String secure(ModelMap model) {
        model.addAttribute("secure_attr1", ".antMatchers(\"/secure\")");
        return "secured";
    }
    @RequestMapping("/secure/extra")
    public String secureExtra(ModelMap model) {
        model.addAttribute("secure_attr1", "extra_secure_attr1");
        return "secured";
    }

    @RequestMapping("/user/{id}")
    public String user(ModelMap model, @PathVariable String id) {
        model.addAttribute("user_attr1", "user_attr1");
        return "user";
    }
}
