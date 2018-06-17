package com.oop2.tim6.NakitWebTim6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.model.Person;
import com.oop2.tim6.NakitWebTim6.repository.IKorisnikJpaRepo;

@Controller
public class HelloController {
    
	@Autowired
	IKorisnikJpaRepo kJP;
	
    @RequestMapping("/user/person")
    public String person(Model model){
        Person p = new Person();
        p.setName("MArko");
        p.setAge("123342");
        model.addAttribute("person", p);

        return "personView";
    }
    
    @RequestMapping("/user/prikazKorisnika")
    public String getAll(Model m){
    	List<Korisnik> kor = kJP.findAll();
    	
    	m.addAttribute("korisnik", kor);
    	
        return "prikazKorisnika";
    }
}
