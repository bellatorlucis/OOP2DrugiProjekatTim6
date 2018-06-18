package com.oop2.tim6.NakitWebTim6.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.model.Ponuda;
import com.oop2.tim6.NakitWebTim6.model.SearchCriteria;
import com.oop2.tim6.NakitWebTim6.service.IOglasServiceTim6;
import com.oop2.tim6.NakitWebTim6.service.IPonudaServiceTim6;

@Controller
@RequestMapping(value="/profil")
public class ProfilControllerTim6 {
	
	private IOglasServiceTim6 oglasService;
	private IPonudaServiceTim6 ponudaService;
	
	@RequestMapping(value="/sviOglasiKorisnika", method=RequestMethod.GET)
	public String getAllKorisniciById(Model m, HttpSession session) {
		List<SearchCriteria> searchCriterias = new ArrayList<SearchCriteria>();
		searchCriterias.add(new SearchCriteria("idOgla", "1", true));
		searchCriterias.add(new SearchCriteria("nakit.tip.idTipa", "1", true));
		//List<Ogla> oglasi = oglasService.getOglasByFilters(searchCriterias);
		
		String korisnickoIme = session.getAttribute("korisnik").toString();
		
		List<Ponuda> ponude = ponudaService.getAllPonudeByKorisnik(korisnickoIme);
		m.addAttribute("ponude", ponude);
		
		List<Ogla> oglasi = oglasService.sviOglasiByKorisnickoIme(korisnickoIme);
		m.addAttribute("oglasi", oglasi);
		
		return "pocetna/index";
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
