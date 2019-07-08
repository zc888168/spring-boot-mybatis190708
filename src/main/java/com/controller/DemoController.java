package com.controller;

import com.base.dao.CountryDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/demo")
public class DemoController {
    @Resource
    private CountryDao countryDao ;

    @GetMapping("/index")
    public String index(){
       int count = countryDao.count();
       return count + "";
    }
}
