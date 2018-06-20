package com.oop2.tim6.NakitWebTim6.service;

import java.util.List;

import com.oop2.tim6.NakitWebTim6.repository.IOglasJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.model.Ponuda;
import com.oop2.tim6.NakitWebTim6.repository.IPonudaCrudRepo;
import com.oop2.tim6.NakitWebTim6.repository.IPonudaJpaRepo;

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
	public List<Ponuda> getAllPonudeByKorisnik(String korisnickoIme) {
		List<Ponuda> list = ponudaRepo.getAllPonudeByKorisnik(korisnickoIme);
		return list;
	}

	@Override
	public Ponuda getTrenutnuPonuduZaOglasId(int oglasId) {
		List<Ponuda> p = ponudaRepo.getPonudeZaOglasId(oglasId);

		if(p.size() >= 1)
			return p.get(p.size() - 1);

		return null;
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
