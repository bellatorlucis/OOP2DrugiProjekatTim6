package com.oop2.tim6.NakitWebTim6.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oop2.tim6.NakitWebTim6.model.Komentar;
import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.repository.IKomentarCrudRepo;
import com.oop2.tim6.NakitWebTim6.repository.IKomentarJpaRepository;

@Service
public class KomentarServiceTim6 implements IKomentarServiceTim6{

	private IKomentarJpaRepository komentarRepo;
	private IKomentarCrudRepo komentarCrudRepo;
	
	@Override
	public List<Komentar> getKomentariZaOglas(Ogla o) {
		List<Komentar> komentariZaOglas = komentarRepo.getKomentariZaOglasId(o.getIdOgla());
		List<Komentar> parentKomentari = komentariZaOglas.stream()
				.filter(komentar -> komentar.getKomentarRoditeljId() == 0)
				.collect(Collectors.toList());
		for(Komentar k: parentKomentari) {
			komentariZaOglas.stream()
				.filter(komentar -> komentar.getKomentarRoditeljId() ==k.getIdKomentara())
				.collect(Collectors.toList());
		}
		
		return parentKomentari;
	}

	@Override
	public Komentar dodajKomentar(Komentar k) {
		return komentarCrudRepo.save(k);
	}
	
	@Autowired
	public void setKomentarRepo(IKomentarJpaRepository komentarRepo) {
		this.komentarRepo = komentarRepo;
	}
	
	@Autowired
	public void setKomentarRepo(IKomentarCrudRepo komentarCrudRepo) {
		this.komentarCrudRepo = komentarCrudRepo;
	}
}
