package com.xy.async.controller;

import com.xy.async.domain.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jinchaoqun|
 * @Date: 2024/7/31 16:06
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/1")
    public String test1() {
        return testService.test();
    }

    @GetMapping("/2")
    public String test2(@RequestParam("name") String name) {
        return testService.test2(name);
    }
}
