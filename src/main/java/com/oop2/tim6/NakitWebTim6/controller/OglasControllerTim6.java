package com.oop2.tim6.NakitWebTim6.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.oop2.tim6.NakitWebTim6.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oop2.tim6.NakitWebTim6.service.IKomentarServiceTim6;
import com.oop2.tim6.NakitWebTim6.service.INakitServiceTim6;
import com.oop2.tim6.NakitWebTim6.service.IOglasServiceTim6;
import com.oop2.tim6.NakitWebTim6.service.IPonudaServiceTim6;
import com.oop2.tim6.NakitWebTim6.service.ITipServiceTim6;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value="/oglas")
public class OglasControllerTim6 {
	
	private ITipServiceTim6 tipService;
	private INakitServiceTim6 nakitService;
	private IOglasServiceTim6 oglasService;
	private IPonudaServiceTim6 ponudaService;
	private IKomentarServiceTim6 komentarService;

	@RequestMapping(value= "/dodajNovi", method=RequestMethod.GET)
	public String getAllTipovi(Model m, HttpServletRequest request) {
		List<Tip> tipoviNakita = tipService.getAllTipoviNakita();
		request.getSession().setAttribute("tipovi", tipoviNakita);
		m.addAttribute("tipovi", tipoviNakita);
		
		return "korisnik/dodajOglas";
	}
	
	@RequestMapping(value= "/dodajNovi", method=RequestMethod.POST)
	public String dodavanjeOglasa(@Valid @ModelAttribute("oglas") Ogla oglas,BindingResult bindingResult, Model m, @RequestParam("file") MultipartFile file,  HttpSession session) throws IOException {
		Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

		if(bindingResult.hasErrors())
		    return "korisnik/dodajOglas";
		
		if (file != null) 
			oglas.getNakit().setSlikaNakita(file.getBytes());

		
		nakitService.dodajNoviNakit(oglas.getNakit());
		
		oglas.setAktivan(1);
		oglas.setKorisnik(korisnik);
		LocalDateTime ldt = LocalDateTime.now();	
		oglas.setDatumVreme(java.sql.Timestamp.valueOf(ldt));
		oglasService.dodajOglas(oglas);

		m.addAttribute("oglas", new Ogla());


		return "korisnik/dodajOglas";
	}

	
	@RequestMapping(value ="/rezultatiPretrage", method=RequestMethod.POST)
    public String getOglasiBy(@ModelAttribute("oglasSearchDto") OglasSearchDto searchConfiguration, Model model) {
		List<Ogla> oglasi = oglasService.getOglasByFilters(searchConfiguration.generateQueryExtensionForOglas());
		List<Tip> tipovi = tipService.getAllTipoviNakita();
    	Tip svi = new Tip();
    	svi.setIdTipa(0);
    	svi.setNaziv("Svi");
    	tipovi.add(svi);
    	model.addAttribute("tipovi", tipovi);
		model.addAttribute("oglasi", oglasi);
    	return "korisnik/sviOglasi";
    }
    
	@RequestMapping(value = "/svi", method = RequestMethod.GET)
	public String sviOglasi(Model model) {

	    List<Ogla> oglasi = oglasService.getAllOglasi();
	    List<Tip> tipovi = tipService.getAllTipoviNakita();
    	Tip svi = new Tip();
    	svi.setIdTipa(0);
    	svi.setNaziv("Svi");
    	tipovi.add(svi);
    	model.addAttribute("tipovi", tipovi);
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
    
    @GetMapping (value = "/detaljiOglasa")
    public String getOglasByID(@RequestParam(value = "id_oglas") int id_oglas,  Model m) {
		Ogla oglas = oglasService.getOglasWithId(id_oglas);
		Ponuda ponuda = ponudaService.getTrenutnuPonuduZaOglasId(id_oglas);

		List<Komentar> komentariZaOglas = komentarService.getKomentariZaOglas(oglas);
		m.addAttribute("komentari", komentariZaOglas);

		m.addAttribute("oglas",oglas);

		if(ponuda != null){
			m.addAttribute("ponuda",ponuda);
		}
		else{
			m.addAttribute("ponuda", new Ponuda());
			m.addAttribute("nemaPonuda", "Za oglas trenutno ne postoje ponude.");}

		
		return "korisnik/detaljiOglasa";
    }

    @GetMapping(value = "/prihvatiPonudu/{id_oglas}")
	public RedirectView prihvatiPonudu(@PathVariable("id_oglas")int id_oglas, Model model, RedirectAttributes redirectAttributes){
		oglasService.prihvatiPonuduZaOglas(id_oglas);

		model.addAttribute("uspesnoPrihvacenaPonuda","Prihvatili ste ponudu za vas oglas");

		redirectAttributes.addAttribute("id_oglas",id_oglas);

		return new RedirectView("/oglas/detaljiOglasa", true);
    }

    @PostMapping (value = "/dodajKomentar/{id_oglas}")
    public RedirectView addKomentar(@ModelAttribute("komentar") Komentar komentar, @PathVariable int id_oglas, HttpSession session, RedirectAttributes redirectAttributes) {
    	Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
    	Ogla oglas = oglasService.getOglasWithId(id_oglas);
    	Nakit nakit = nakitService.getNakitByIdOglasa(id_oglas);

    	oglas.setNakit(nakit);
    	komentar.setKorisnik(korisnik);
		komentar.setOgla(oglas);

		LocalDateTime ldt = LocalDateTime.now();
		komentar.setDatumVreme(java.sql.Timestamp.valueOf(ldt));



		komentarService.dodajKomentar(komentar);
		redirectAttributes.addAttribute("id_oglas",id_oglas);

		return new RedirectView("/oglas/detaljiOglasa", true);
    }

    @PostMapping(value = "/dodajPonudu")
	public RedirectView dodajPonuduZaOglas(@ModelAttribute("ponuda")Ponuda ponuda, RedirectAttributes redirectAttributes){
		boolean dodata = ponudaService.dodajNovuPonudu(ponuda);


		redirectAttributes.addFlashAttribute("ponudaDodata", dodata);


		redirectAttributes.addAttribute("id_oglas",ponuda.getOgla().getIdOgla());
		return new RedirectView("/oglas/detaljiOglasa",true);
	}
	
	@ModelAttribute("oglas")
	public Ogla getOglas(){
		return new Ogla();
	}
	
	@ModelAttribute("komentar")
	public Komentar getKomentar() {
		return new Komentar();
	}

	@ModelAttribute("oglasSearchDto")
	public OglasSearchDto getOglasSearchDto(){
		return new OglasSearchDto();
	}

	@ModelAttribute("ponuda")
	public Ponuda getPonuda(){return  new Ponuda();}
	
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
	
	@Autowired
	public void setKomentarService(IKomentarServiceTim6 komentarService) {
		this.komentarService = komentarService;
	}

}