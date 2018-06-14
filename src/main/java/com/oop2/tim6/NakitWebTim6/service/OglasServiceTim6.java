package com.oop2.tim6.NakitWebTim6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.repository.OglasJpaRepo;

@Service
public class OglasServiceTim6 implements IOglasServiceTim6 {

	private OglasJpaRepo oJP;
	
	@Override
	public List<Ogla> getAllOglasiByIdKorisnika() {
		int idKorisnika = 1; // treba implementirati, idKorisnika ulogovanog
		List<Ogla> oglasi = oJP.MojfindById(idKorisnika); 
		
		return oglasi;
	}

	@Autowired
	public void setoJP(OglasJpaRepo oJP) {
		this.oJP = oJP;
	}
	
}
