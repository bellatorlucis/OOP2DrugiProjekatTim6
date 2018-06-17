package com.oop2.tim6.NakitWebTim6.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.model.Ponuda;
import com.oop2.tim6.NakitWebTim6.repository.IPonudaJpaRepo;

@Service
public class PonudaServiceTim6 implements IPonudaServiceTim6{

	IPonudaJpaRepo ponudaRepo;
	
	@Autowired
	public void setPonudaRepo(IPonudaJpaRepo ponudaRepo) {
		this.ponudaRepo = ponudaRepo;
	}
	
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
		Ponuda p = ponudaRepo.getTrenutnuPonuduZaOglasId(oglasId);
		return p;
	}

	@Override
	public Ponuda dodajNovuPonudu(Ogla oglas, Korisnik korisnik, int ponudaPare) {
		Ponuda p = kreirajNovuPonudu(oglas, korisnik, ponudaPare);
		return ponudaRepo.dodajPonudu(p);
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
