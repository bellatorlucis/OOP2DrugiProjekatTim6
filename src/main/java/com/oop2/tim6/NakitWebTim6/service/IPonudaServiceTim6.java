package com.oop2.tim6.NakitWebTim6.service;

import java.util.List;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.model.Ponuda;

public interface IPonudaServiceTim6 {
	
	public List<Ponuda> getAllPonudeByKorisnik(String korisnickoIme);
	
	public Ponuda getTrenutnuPonuduZaOglasId(int oglasId);
	
	public boolean dodajNovuPonudu(Ponuda ponuda);
}
