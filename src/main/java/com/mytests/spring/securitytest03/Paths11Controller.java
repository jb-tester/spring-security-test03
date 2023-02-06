package com.mytests.spring.securitytest03;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * this controller is for configuration
 * com.mytests.spring.securitytest03.SecurityConfig.Path11SecurityConfigurationAdapter
 */
@Controller
public class Paths11Controller {

    @RequestMapping("/path11")
    public String path11(ModelMap model) {
        model.addAttribute("t1", "mvcMatcher(\"/path11/**\"): for VIP, ADMIN");
        return "t1";
    }

}
