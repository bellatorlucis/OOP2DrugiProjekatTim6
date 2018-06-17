package com.oop2.tim6.NakitWebTim6.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.model.Ponuda;
import com.oop2.tim6.NakitWebTim6.repository.IPonudaJpaRepo;
import com.oop2.tim6.NakitWebTim6.repository.IKorisnikCrudRepo;
import com.oop2.tim6.NakitWebTim6.repository.IPonudaCrudRepo;

@Service
public class PonudaServiceTim6 implements IPonudaServiceTim6{

	IPonudaJpaRepo ponudaRepo;
	IPonudaCrudRepo ponudaCrudRepo;
	
	@Autowired
	public void setPonudaRepo(IPonudaJpaRepo ponudaRepo) {
		this.ponudaRepo = ponudaRepo;
	}
	
	@Autowired
	public void setKorisnikCrudRepo(IPonudaCrudRepo ponudaCrudRepo) { this.ponudaCrudRepo = ponudaCrudRepo; }
	
	@Override
	public List<Ponuda> getAllPonudaByIDKorisnika() {
		int idKorisnika = 2;//TO DO NEMANJA :D
		List<Ponuda> list = ponudaRepo.getAllPonudeByIDKorisnik(idKorisnika);
		//?
		list.stream().map(x->x.getKorisnik().getIdKorisnika()).distinct().collect(Collectors.toList());
		return list;
	}

	@Override
	public Ponuda getTrenutnuPonuduZaOglasId(int oglasId) {
		List<Ponuda> p = ponudaRepo.getPonudeZaOglasId(oglasId);
		return p.get(0);
	}

	@Override
	public Ponuda dodajNovuPonudu(Ogla oglas, Korisnik korisnik, int ponudaPare) {
		Ponuda p = kreirajNovuPonudu(oglas, korisnik, ponudaPare);
		return ponudaCrudRepo.save(p);
	}
	
	private Ponuda kreirajNovuPonudu(Ogla oglas, Korisnik korisnik, int ponudaPare) {
		Ponuda p = new Ponuda();
		p.setPonudaPare(ponudaPare);;
		p.setKorisnik(korisnik);
		p.setOgla(oglas);
		p.setDateTime();
		return p;
	}
}
