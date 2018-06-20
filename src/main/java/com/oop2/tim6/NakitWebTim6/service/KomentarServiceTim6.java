package com.oop2.tim6.NakitWebTim6.service;

import java.util.List;
import java.util.stream.Collectors;

import com.oop2.tim6.NakitWebTim6.model.Komentar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.repository.IKomentarCrudRepoTim6;
import com.oop2.tim6.NakitWebTim6.repository.IKomentarJpaRepositoryTim6;

@Service
public class KomentarServiceTim6 implements IKomentarServiceTim6{

	private IKomentarJpaRepositoryTim6 komentarRepo;
	private IKomentarCrudRepoTim6 komentarCrudRepo;
	
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
	public void setKomentarRepo(IKomentarJpaRepositoryTim6 komentarRepo) {
		this.komentarRepo = komentarRepo;
	}
	
	@Autowired
	public void setKomentarRepo(IKomentarCrudRepoTim6 komentarCrudRepo) {
		this.komentarCrudRepo = komentarCrudRepo;
	}
}
