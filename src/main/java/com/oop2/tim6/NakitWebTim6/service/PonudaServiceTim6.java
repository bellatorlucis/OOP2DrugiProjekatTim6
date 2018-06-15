package com.oop2.tim6.NakitWebTim6.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oop2.tim6.NakitWebTim6.model.Ponuda;
import com.oop2.tim6.NakitWebTim6.repository.PonudaJpaRepo;

@Service
public class PonudaServiceTim6 implements IPonudaServiceTim6{

	PonudaJpaRepo ponudaRepo;
	
	@Override
	public List<Ponuda> getAllPonudaByIDKorisnika() {
		int idKorisnika = 2;//TO DO NEMANJA :D
		List<Ponuda> list = ponudaRepo.getAllPonudeByIDKorisnik(idKorisnika);
		//?
		list.stream().map(x->x.getKorisnik().getIdKorisnika()).distinct().collect(Collectors.toList());
		return list;
	}

	
	@Autowired
	public void setPonudaRepo(PonudaJpaRepo ponudaRepo) {
		this.ponudaRepo = ponudaRepo;
	}
	
	
	
	
}
