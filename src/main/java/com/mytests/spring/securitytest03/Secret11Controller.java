package com.mytests.spring.securitytest03;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * this controller is for configuration
 * com.mytests.spring.securitytest03.SecurityConfig.SecretRegexpSecurityConfigurationAdapter
 */
@Controller
public class Secret11Controller {


    @RequestMapping("/secret11")
    public String secret11(ModelMap model) {
        model.addAttribute("t1", "regexMatcher(\"/secret\\\\d[\\\\d]*\"): for ADMIN");
        return "t1";
    }
}
