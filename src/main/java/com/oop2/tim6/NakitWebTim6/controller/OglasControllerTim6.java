package com.oop2.tim6.NakitWebTim6.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.model.Nakit;
import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.model.Tip;
import com.oop2.tim6.NakitWebTim6.repository.IKorisnikJpaRepo;
import com.oop2.tim6.NakitWebTim6.service.INakitServiceTim6;
import com.oop2.tim6.NakitWebTim6.service.IOglasServiceTim6;
import com.oop2.tim6.NakitWebTim6.service.IPonudaServiceTim6;
import com.oop2.tim6.NakitWebTim6.service.ITipServiceTim6;

@Controller
@RequestMapping(value="/oglas")
public class OglasControllerTim6 {
	
	private ITipServiceTim6 tipService;
	private INakitServiceTim6 nakitService;
	

	private IOglasServiceTim6 oglasService;
	private IKorisnikJpaRepo korisnikRepo;
	private IPonudaServiceTim6 ponudaService;
	
	@RequestMapping(value= "/dodajNovi", method=RequestMethod.GET)
	public String getAllTipovi(Model m, HttpServletRequest request) {
		List<Tip> tipoviNakita = tipService.getAllTipoviNakita();
		request.getSession().setAttribute("tipovi", tipoviNakita);
		m.addAttribute("tipovi", tipoviNakita);
		
		return "korisnik/dodajOglas";
	}
	
	@RequestMapping(value= "/dodajNovi", method=RequestMethod.POST)
	public String dodavanjeOglasa(@ModelAttribute("oglas") Ogla oglas, Model m, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException { 
		Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
		
		if (file != null) 
			oglas.getNakit().setSlikaNakita(file.getBytes());
		
		nakitService.dodajNoviNakit(oglas.getNakit());
		
		oglas.setAktivan(1);
		oglas.setKorisnik(korisnik);
		oglasService.dodajOglas(oglas);
		m.addAttribute("oglas", oglas);
		
		oglas.getNakit().setBoja("");
		oglas.getNakit().setMaterijal("");
		oglas.getNakit().setSlikaNakita(null);
		oglas.setTekst("");
		oglas.setNaslov("");
		oglas.setMinPonuda(0.0);
		
		return "korisnik/dodajOglas";
	}

	@RequestMapping(value = "/svi", method = RequestMethod.GET)
	public String sviOglasi(Model model) {

	    List<Ogla> oglasi = oglasService.getAllOglasi();
	    model.addAttribute("oglasi", oglasi);

        return "korisnik/sviOglasi";
    }

    @RequestMapping(value = "/oglasSlika/{id_oglas}")
    @ResponseBody
    public byte[] getImageKorisnika(@PathVariable int id_oglas)  {

	    Ogla oglas = oglasService.getOglasWithId(id_oglas);
        Nakit nakit = oglas.getNakit();

        return nakit.getSlikaNakita();
    }
	
	@ModelAttribute("oglas")
	public Ogla getOglas(){
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

	@Autowired
	public void setKorisnikRepo(IKorisnikJpaRepo korisnikRepo) {
		this.korisnikRepo = korisnikRepo;
	}
	@Autowired
	public void setPonudaService(IPonudaServiceTim6 ponudaService) {
		this.ponudaService = ponudaService;
	}
	
}