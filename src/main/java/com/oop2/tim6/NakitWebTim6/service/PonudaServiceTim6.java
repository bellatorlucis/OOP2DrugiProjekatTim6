package com.oop2.tim6.NakitWebTim6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.model.Ponuda;
import com.oop2.tim6.NakitWebTim6.repository.IPonudaCrudRepoTim6;
import com.oop2.tim6.NakitWebTim6.repository.IPonudaJpaRepoTim6;

@Service
public class PonudaServiceTim6 implements IPonudaServiceTim6{

	IPonudaJpaRepoTim6 ponudaRepo;
	IPonudaCrudRepoTim6 ponudaCrudRepo;

	
	@Autowired
	public void setPonudaRepo(IPonudaJpaRepoTim6 ponudaRepo) {
		this.ponudaRepo = ponudaRepo;
	}
	
	@Autowired
	public void setKorisnikCrudRepo(IPonudaCrudRepoTim6 ponudaCrudRepo) { this.ponudaCrudRepo = ponudaCrudRepo; }

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
