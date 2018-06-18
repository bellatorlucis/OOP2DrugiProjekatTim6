package com.oop2.tim6.NakitWebTim6.service;

import java.util.List;

import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.model.SearchCriteria;

public interface IOglasServiceTim6 {
	
	public List<Ogla> sviOglasiByKorisnickoIme(String korisnickoIme);
	
	public List<Ogla> getAllOglasi();

	public List<Ogla> getOglasByFilters(List<SearchCriteria> searchCriteria);
	
	public Ogla dodajOglas(Ogla oglas);
	
}