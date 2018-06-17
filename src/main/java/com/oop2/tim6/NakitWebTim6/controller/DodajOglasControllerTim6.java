package com.oop2.tim6.NakitWebTim6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oop2.tim6.NakitWebTim6.model.Tip;
import com.oop2.tim6.NakitWebTim6.service.ITipServiceTim6;

@Controller
@RequestMapping(value="/oglas")
public class DodajOglasControllerTim6 {
	
	private ITipServiceTim6 tipService;
	
	@GetMapping(value="/sviTipoviNakita")
	public String getAllTipoviNakita(Model m) {
		List<Tip> tipoviNakita = tipService.getAllTipoviNakita();
		m.addAttribute("tipovi", tipoviNakita);
		
		return "dodajOglas";
	}
	
	@Autowired
	public void setTipService(ITipServiceTim6 tipService) {
		this.tipService = tipService;
	}
	
}
