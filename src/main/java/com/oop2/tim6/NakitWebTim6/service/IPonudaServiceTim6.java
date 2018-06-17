package com.oop2.tim6.NakitWebTim6.service;

import java.util.List;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.model.Ponuda;

public interface IPonudaServiceTim6 {
	
	public List<Ponuda> getAllPonudaByIDKorisnika();
	
	public Ponuda getTrenutnuPonuduZaOglasId(int oglasId);
	
	public Ponuda dodajNovuPonudu(Ogla oglas, Korisnik korisnik, int ponudaPare);
}
