package com.oop2.tim6.NakitWebTim6.service;

import java.util.List;

import com.oop2.tim6.NakitWebTim6.model.Komentar;
import com.oop2.tim6.NakitWebTim6.model.Ogla;

public interface IKomentarServiceTim6 {

	public List<Komentar> getKomentariZaOglas(Ogla o);
	
	public Komentar dodajKomentar(Komentar k);
}
