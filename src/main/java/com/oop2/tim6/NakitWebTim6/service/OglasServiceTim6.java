package com.oop2.tim6.NakitWebTim6.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.repository.IOglasJpaRepo;

@Service
public class OglasServiceTim6 implements IOglasServiceTim6 {

	private IOglasJpaRepo oJP;
	
	@Override
	public List<Ogla> getAllOglasiByIdKorisnika() {
		int idKorisnika = 1; // treba implementirati, idKorisnika ulogovanog
		List<Ogla> oglasi = oJP.MojfindById(idKorisnika);
		List<Ogla> noviOglas = oglasi.stream().sorted(Comparator.comparing(Ogla::getAktivan).reversed())
				.collect(Collectors.toList());

		return noviOglas;
	}

	@Autowired
	public void setoJP(IOglasJpaRepo oJP) {
		this.oJP = oJP;
	}
}