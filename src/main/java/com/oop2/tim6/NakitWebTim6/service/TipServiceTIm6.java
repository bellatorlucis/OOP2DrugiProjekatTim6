package com.oop2.tim6.NakitWebTim6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oop2.tim6.NakitWebTim6.model.Tip;

@Service
public class TipServiceTIm6 implements ITipServiceTim6 {
	
	ITipServiceTim6 iTS;
	
	@Override
	public List<Tip> getAllTipoviNakita() {
		List<Tip> tipoviNakita = iTS.getAllTipoviNakita(); 
		return tipoviNakita;
	}
	
	@Autowired
	public void setiTS(ITipServiceTim6 iTS) {
		this.iTS = iTS;
	}

}
