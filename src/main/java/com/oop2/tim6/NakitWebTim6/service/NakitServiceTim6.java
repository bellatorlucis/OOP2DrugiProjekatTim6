package com.oop2.tim6.NakitWebTim6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oop2.tim6.NakitWebTim6.model.Nakit;
import com.oop2.tim6.NakitWebTim6.repository.INakitCrudRepoTim6;

@Service
public class NakitServiceTim6 implements INakitServiceTim6 {
	
	INakitCrudRepoTim6 nCR;
	
	@Override
	public Nakit dodajNoviNakit(Nakit nakit) {
		Nakit noviNakit = nCR.save(nakit);
		
		return noviNakit;
	}

	@Autowired
	public void setnCR(INakitCrudRepoTim6 nCR) {
		this.nCR = nCR;
	}

}