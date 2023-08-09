package com.mytests.spring.securitytest03;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * *
 * <p>Created by irina on 4/6/2023.</p>
 * <p>Project: spring-security-test03</p>
 * *
 */
@RestController
@RequestMapping("/extra")
public class ExtraController {

    @GetMapping
    public String root() {
        return "extra";
    }

    @GetMapping("/{pv}")
    public String pathvar(@PathVariable String pv) {
        return "extra/" + pv;
    }

    @GetMapping("/{pv1}/{pv2}")
    public String pathvars(@PathVariable("pv1") String pv1, @PathVariable String pv2) {
        return "extra/" + pv1 + "/" + pv2;
    }

}
