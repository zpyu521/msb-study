package com.zpyu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: zpyu521
 * @Date: 2019/8/16
 * @Description:
 * @Version: 1.0
 */
@Controller
@RequestMapping("/profile")
public class WxProfileController {


    @RequestMapping("/index")
    @ResponseBody
    public String index(){

        return "565";
    }







}
