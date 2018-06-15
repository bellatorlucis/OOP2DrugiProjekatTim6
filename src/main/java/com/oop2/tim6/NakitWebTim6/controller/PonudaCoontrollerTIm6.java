package com.oop2.tim6.NakitWebTim6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oop2.tim6.NakitWebTim6.model.Ponuda;
import com.oop2.tim6.NakitWebTim6.service.IPonudaServiceTim6;

@Controller
@RequestMapping (value="/ponuda")
public class PonudaCoontrollerTIm6 {
	
	IPonudaServiceTim6 service;

	@RequestMapping (value="/getPonuda")
	public String getAllPonudeByIDKorisnik(Model m) {
		List<Ponuda> list = service.getAllPonudaByIDKorisnika();
		m.addAttribute("ponudeZaKorisnika", list);
		return "sviOglasi";
	}
	
	
	@Autowired
	public void setService(IPonudaServiceTim6 service) {
		this.service = service;
	}
	
	
}
