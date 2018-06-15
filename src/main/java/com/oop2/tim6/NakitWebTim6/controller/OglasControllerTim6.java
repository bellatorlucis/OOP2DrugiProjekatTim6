package com.oop2.tim6.NakitWebTim6.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.criterion.Distinct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.model.Ponuda;
import com.oop2.tim6.NakitWebTim6.service.IOglasServiceTim6;
import com.oop2.tim6.NakitWebTim6.service.IPonudaServiceTim6;

@Controller
@RequestMapping(value="/profil")
public class OglasControllerTim6 {
	
	private IOglasServiceTim6 oST;
	private IPonudaServiceTim6 pST;
	
	@RequestMapping(value="/sviOglasiKorisnika")
	public String getAllKorisniciById(Model m) {
		List<Ogla> oglasi = oST.getAllOglasiByIdKorisnika();
		m.addAttribute("oglasi", oglasi);
		List<Ponuda> list = pST.getAllPonudaByIDKorisnika();
		
		m.addAttribute("ponudeZaKorisnika", list);
		return "sviOglasi";
	}
	

	

	@Autowired
	public void setoST(IOglasServiceTim6 oST) {
		this.oST = oST;
	}



	@Autowired
	public void setpST(IPonudaServiceTim6 pST) {
		this.pST = pST;
	}
	
	
	
}
