package com.oop2.tim6.NakitWebTim6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oop2.tim6.NakitWebTim6.model.Tip;
import com.oop2.tim6.NakitWebTim6.service.ITipServiceTim6;

@Controller
@RequestMapping(value="/oglas")
public class DodajOglasControllerTim6 {
	
	private ITipServiceTim6 tipService;
	
	@RequestMapping(value="/sviTipoviNakita", method=RequestMethod.GET)
	public String getAllTipovi(Model m) {
		List<Tip> tipoviNakita = tipService.getAllTipoviNakita();
		m.addAttribute("tipovi", tipoviNakita);
		
		return "dodajOglas";
	}
	
	@Autowired
	public void setTipService(ITipServiceTim6 tipService) {
		this.tipService = tipService;
	}
	
}
