package com.oop2.tim6.NakitWebTim6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.service.IOglasServiceTim6;

@Controller
@RequestMapping(value="/oglas")
public class OglasControllerTim6 {
	
	private IOglasServiceTim6 oST;
	
	@RequestMapping(value="/sviOglasiKorisnika")
	public String getAllKorisniciById(Model m) {
		List<Ogla> oglasi = oST.getAllOglasiByIdKorisnika();
		m.addAttribute("oglasi", oglasi);
		
		return "sviOglasi";
	}

	@Autowired
	public void setoST(IOglasServiceTim6 oST) {
		this.oST = oST;
	}
	
	
	
}
