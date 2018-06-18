package com.oop2.tim6.NakitWebTim6.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.model.Tip;
import com.oop2.tim6.NakitWebTim6.repository.KorisnikRepo;
import com.oop2.tim6.NakitWebTim6.service.INakitServiceTim6;
import com.oop2.tim6.NakitWebTim6.service.IOglasServiceTim6;
import com.oop2.tim6.NakitWebTim6.service.ITipServiceTim6;

@Controller
@RequestMapping(value="/oglas")
public class DodajOglasControllerTim6 {
	
	private ITipServiceTim6 tipService;
	private INakitServiceTim6 nakitService;
	private IOglasServiceTim6 oglasService;
	
	@Autowired
	KorisnikRepo kR;
	
	@RequestMapping(value="/sviTipoviNakita", method=RequestMethod.GET)
	public String getAllTipovi(Model m, HttpServletRequest request) {
		List<Tip> tipoviNakita = tipService.getAllTipoviNakita();
		request.getSession().setAttribute("tipovi", tipoviNakita);
		m.addAttribute("tipovi", tipoviNakita);
		
		return "dodajOglas";
	}
	
	@RequestMapping(value="/dodavanjeOglasa", method=RequestMethod.POST)
	public String dodavanjeOglasa(@ModelAttribute("oglas") Ogla oglas, Model m) throws IOException { // @RequestParam("slikaNakita") MultipartFile file
		Korisnik k = new Korisnik();
		k.setIdKorisnika(2);
		oglas.setKorisnik(k);
		
		/**if (file != null) 
			oglas.getNakit().setSlikaNakita(file.getBytes());**/
		
		nakitService.dodajNoviNakit(oglas.getNakit());
		//m.addAttribute("nakit", oglas.getNakit());
		
		oglas.setAktivan(1);
		oglasService.dodajOglas(oglas);
		m.addAttribute("oglas", oglas);
		
		oglas.getNakit().setBoja("");
		oglas.getNakit().setMaterijal("");
		oglas.getNakit().setSlikaNakita(null);
		oglas.setTekst("");
		oglas.setNaslov("");
		oglas.setMinPonuda(0.0);
		
		return "dodajOglas";
	}
	
	@ModelAttribute("oglas")
	public Ogla getOglas(){
		System.out.println("OVOOO JE OGLAS");
		return new Ogla();
	}
	
	@Autowired
	public void setTipService(ITipServiceTim6 tipService) {
		this.tipService = tipService;
	}
	
	@Autowired
	public void setNakitService(INakitServiceTim6 nakitService) {
		this.nakitService = nakitService;
	}

	@Autowired
	public void setOglasService(IOglasServiceTim6 oglasService) {
		this.oglasService = oglasService;
	}
	
}