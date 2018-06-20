package com.oop2.tim6.NakitWebTim6.service;

import java.util.List;

import com.oop2.tim6.NakitWebTim6.model.Ogla;

public interface IOglasServiceTim6 {
	
	public List<Ogla> sviOglasiByKorisnickoIme(String korisnickoIme);
	
	public List<Ogla> getAllOglasi();

	public List<Ogla> getOglasByFilters(String extensionQuery);
	
	public Ogla dodajOglas(Ogla oglas);

	public Ogla getOglasWithId(int id);
	public void prihvatiPonuduZaOglas(int id_oglas);
	
}