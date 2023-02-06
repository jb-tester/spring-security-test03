package com.mytests.spring.securitytest03;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * this controller is for configuration
 * com.mytests.spring.securitytest03.SecurityConfig.Path1SecurityConfigurationAdapter
 */
@Controller
public class Paths1Controller {

    @RequestMapping("/path1")
    public String path11(ModelMap model) {
        model.addAttribute("t1", "mvcMatcher(\"/path11/**\"): for  ADMIN");
        return "t1";
    }

}
