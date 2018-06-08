package com.oop2.tim6.NakitWebTim6.controller;


import com.oop2.tim6.NakitWebTim6.model.Person;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        return "Hello World";
    }

    @RequestMapping("/person")
    public String person(Model model){
        Person p = new Person();
        p.setName("MArko");
        p.setAge("123342");
        model.addAttribute("person", p);

        return "personView";

    }
}
