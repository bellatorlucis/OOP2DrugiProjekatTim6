package com.oop2.tim6.NakitWebTim6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oop2.tim6.NakitWebTim6.model.Ponuda;

@Repository
public interface IPonudaJpaRepo extends JpaRepository<Ponuda, Integer> {																							
	@Query("SELECT p FROM Ponuda p WHERE p.korisnik.idKorisnika=:idk")
	public List<Ponuda> getAllPonudeByIDKorisnik(@Param("idk") Integer idKorisnika);
	
	@Query("SELECT p FROM Ponuda p WHERE p.ogla.idOgla=:oglasId ORDER BY p.ponudaPare")
	public List<Ponuda> getPonudeZaOglasId(@Param("oglasId")Integer IdOgla);
}