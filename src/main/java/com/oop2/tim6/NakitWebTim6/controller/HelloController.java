package com.oop2.tim6.NakitWebTim6.controller;


import com.oop2.tim6.NakitWebTim6.model.Person;
import com.oop2.tim6.NakitWebTim6.service.IHelloWordlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {

    @Autowired
    IHelloWordlService service;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @ResponseBody //Ova anotacija vraca String kao responseBody, ne gleda u folderu views da li postoji view sa ovim imenom
    public String hello(){
        String poruka = service.getHelloMessage();

        return poruka;
    }

    @RequestMapping("/user/person")
    public String person(Model model){
        Person p = new Person();
        p.setName("MArko");
        p.setAge("123342");
        model.addAttribute("person", p);

        return "personView";

    }
}
