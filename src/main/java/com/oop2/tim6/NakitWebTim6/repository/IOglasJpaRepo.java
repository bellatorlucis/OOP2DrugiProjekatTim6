package com.oop2.tim6.NakitWebTim6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oop2.tim6.NakitWebTim6.model.Ogla;

@Repository
public interface IOglasJpaRepo extends JpaRepository<Ogla, Integer> {
	
	@Query("SELECT o FROM Ogla o WHERE o.korisnik.korisnickoIme = :ime")
	public List<Ogla> sviOglasiByKorisnickoIme(@Param("ime") String korisnickoIme);

	Ogla findByIdOgla(int id);
}