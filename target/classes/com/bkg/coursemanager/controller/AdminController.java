package com.bkg.coursemanager.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Description Admin接口相关Controller
 * @author bao
 * @version v1.2
 * @date 2018/12/21
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public String getAdminEmail() {
        return "123456@qq.com";
    }
}
