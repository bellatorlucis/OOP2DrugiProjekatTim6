package com.oop2.tim6.NakitWebTim6.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oop2.tim6.NakitWebTim6.model.Komentar;
import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.model.Ponuda;
import com.oop2.tim6.NakitWebTim6.model.SearchCriteria;
import com.oop2.tim6.NakitWebTim6.service.IOglasServiceTim6;
import com.oop2.tim6.NakitWebTim6.service.IPonudaServiceTim6;

@Controller
@RequestMapping(value="/profil")
public class OglasControllerTim6 {
	
	private IOglasServiceTim6 oST;
	private IPonudaServiceTim6 pST;
	
	@RequestMapping(value="/sviOglasiKorisnika", method=RequestMethod.GET)
	public String getAllKorisniciById(Model m) {
		List<SearchCriteria> searchCriterias = new ArrayList<SearchCriteria>();
		searchCriterias.add(new SearchCriteria("idOgla", "1", true));
		searchCriterias.add(new SearchCriteria("nakit.tip.idTipa", "1", true));
		List<Ogla> oglasi = oST.getOglasByFilters(searchCriterias);
		m.addAttribute("oglasi", oglasi);
		return "index";
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
