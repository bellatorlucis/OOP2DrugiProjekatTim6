package com.oop2.tim6.NakitWebTim6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oop2.tim6.NakitWebTim6.model.Tip;
import com.oop2.tim6.NakitWebTim6.repository.ITipJpaRepoTim6;

@Service
public class TipServiceTIm6 implements ITipServiceTim6 {
	
	ITipJpaRepoTim6 tJR;
	
	@Override
	public List<Tip> getAllTipoviNakita() {
		List<Tip> tipoviNakita = tJR.findAll(); 
		return tipoviNakita;
	}
	
	@Autowired
	public void settJR(ITipJpaRepoTim6 tJR) {
		this.tJR = tJR;
	}

}
