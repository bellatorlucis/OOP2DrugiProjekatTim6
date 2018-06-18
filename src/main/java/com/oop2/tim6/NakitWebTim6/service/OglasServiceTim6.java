package com.oop2.tim6.NakitWebTim6.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.model.SearchCriteria;
import com.oop2.tim6.NakitWebTim6.repository.IOglasJpaRepo;
import com.oop2.tim6.NakitWebTim6.repository.OglasFilterFindRepo;

@Service
public class OglasServiceTim6 implements IOglasServiceTim6 {

	private IOglasJpaRepo oglasRepo;

	private OglasFilterFindRepo oglasFilterRepo;

	@Override
	public List<Ogla> getAllOglasiByIdKorisnika() {
		int idKorisnika = 1; // treba implementirati, idKorisnika ulogovanog
		List<Ogla> oglasi = oglasRepo.MojfindById(idKorisnika);
		List<Ogla> noviOglas = oglasi.stream().sorted(Comparator.comparing(Ogla::getAktivan).reversed())
				.collect(Collectors.toList());

		return noviOglas;

	}

	@Override
	public List<Ogla> getOglasByFilters(List<SearchCriteria> searchCriterias) {
		String query = generateQueryFromSearchCriteria(searchCriterias);
		return oglasFilterRepo.getOglasiBy(query);
	}

	private String generateQueryFromSearchCriteria(List<SearchCriteria> searchCriterias) {
		SearchCriteria lastSearchCriteria = searchCriterias.remove(searchCriterias.size() - 1);
		String query = "";
		for (SearchCriteria sc : searchCriterias) {
			query += "o." + sc.toString() + " and ";
		}

		return query + lastSearchCriteria.toString();
	}

	@Override
	public List<Ogla> getAllOglasi() {
		return oglasRepo.findAll();
	}
	
	@Override
	public Ogla dodajOglas(Ogla oglas) {
		Ogla noviOglas = oglasRepo.save(oglas);
		
		return noviOglas;
	}
	
	@Autowired
	public void setoJP(IOglasJpaRepo oglasRepo) {
		this.oglasRepo = oglasRepo;
	}
	
	@Autowired
	public void setOglasFilterRepo(OglasFilterFindRepo oglasFilterRepo) {
		this.oglasFilterRepo = oglasFilterRepo;
	}
	
}