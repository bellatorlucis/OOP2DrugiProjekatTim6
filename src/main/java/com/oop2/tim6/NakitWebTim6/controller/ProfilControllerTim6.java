package com.oop2.tim6.NakitWebTim6.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.model.Ponuda;
import com.oop2.tim6.NakitWebTim6.service.IOglasServiceTim6;
import com.oop2.tim6.NakitWebTim6.service.IPonudaServiceTim6;

@Controller
public class ProfilControllerTim6 {
	
	private IOglasServiceTim6 oglasService;
	private IPonudaServiceTim6 ponudaService;
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String getAllKorisniciById(Model m, HttpSession session, HttpServletRequest request) {
		Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
		
		List<Ponuda> ponude = ponudaService.getAllPonudeByKorisnik(korisnik.getKorisnickoIme());
		m.addAttribute("ponude", ponude);
		
		List<Ogla> oglasi = oglasService.sviOglasiByKorisnickoIme(korisnik.getKorisnickoIme());
		m.addAttribute("oglasi", oglasi);
		
		return "korisnik/index";
	}
	
	@Autowired
	public void setoST(IOglasServiceTim6 oglasService) {
		this.oglasService = oglasService;
	}

	@Autowired
	public void setpST(IPonudaServiceTim6 ponudaService) {
		this.ponudaService = ponudaService;
	}
	
}
