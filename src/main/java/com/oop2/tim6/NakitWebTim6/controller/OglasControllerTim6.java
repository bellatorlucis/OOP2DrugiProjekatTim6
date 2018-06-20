package com.oop2.tim6.NakitWebTim6.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.oop2.tim6.NakitWebTim6.model.OglasSearchDto;
import com.oop2.tim6.NakitWebTim6.model.Ponuda;
import com.oop2.tim6.NakitWebTim6.model.Tip;
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
	private IPonudaServiceTim6 ponudaService;
	
	@RequestMapping(value= "/dodajNovi", method=RequestMethod.GET)
	public String getAllTipovi(Model m, HttpServletRequest request) {
		List<Tip> tipoviNakita = tipService.getAllTipoviNakita();
		request.getSession().setAttribute("tipovi", tipoviNakita);
		m.addAttribute("tipovi", tipoviNakita);
		
		return "korisnik/dodajOglas";
	}
	
	@RequestMapping(value= "/dodajNovi", method=RequestMethod.POST)
	public String dodavanjeOglasa(@Valid @ModelAttribute("oglas") Ogla oglas, Model m, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException { 
		Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
		
		if (file != null) 
			oglas.getNakit().setSlikaNakita(file.getBytes());
		
		nakitService.dodajNoviNakit(oglas.getNakit());
		
		oglas.setAktivan(1);
		oglas.setKorisnik(korisnik);
		LocalDateTime ldt = LocalDateTime.now();	
		oglas.setDatumVreme(java.sql.Timestamp.valueOf(ldt));
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

	
	@RequestMapping(value ="/rezultatiPretrage", method=RequestMethod.POST)
    public String getOglasiBy(@ModelAttribute("oglasSearchDto") OglasSearchDto searchConfiguration, Model model) {
		List<Ogla> oglasi = oglasService.getOglasByFilters(searchConfiguration.generateQueryExtensionForOglas());
		model.addAttribute("oglasi", oglasi);
		
    	return "korisnik/sviOglasi";
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
    
    @GetMapping (value = "/detaljiOglasa/{id_oglas}")
    public String getOglasByID(@PathVariable int id_oglas ,Model m) {
		Ogla oglas = oglasService.getOglasWithId(id_oglas);
		Ponuda ponuda = ponudaService.getTrenutnuPonuduZaOglasId(id_oglas);
		m.addAttribute("oglas",oglas);

		if(ponuda != null)
			m.addAttribute("ponuda",ponuda);
		else
			m.addAttribute("nemaPonuda", "Za oglas trenutno ne postoje ponude.");
		
		return "korisnik/detaljiOglasa";
    }

    @GetMapping(value = "/prihvatiPonudu/{id_oglas}")
	public String prihvatiPonudu(@PathVariable("id_oglas")int id_oglas, Model model){
		oglasService.prihvatiPonuduZaOglas(id_oglas);
		Ogla oglas = oglasService.getOglasWithId(id_oglas);
		Ponuda ponuda = ponudaService.getTrenutnuPonuduZaOglasId(id_oglas);

		model.addAttribute("oglas",oglas);
		model.addAttribute("ponuda", ponuda);
		model.addAttribute("uspesnoPrihvacenaPonuda","Prihvatili ste ponudu za vas oglas");

		return "korisnik/detaljiOglasa";
	}
	
	@ModelAttribute("oglas")
	public Ogla getOglas(){
		return new Ogla();
	}
	
	@ModelAttribute("oglasSearchDto")
	public OglasSearchDto getOglasSearchDto(){
		return new OglasSearchDto();
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
	public void setPonudaService(IPonudaServiceTim6 ponudaService) {
		this.ponudaService = ponudaService;
	}
	
}