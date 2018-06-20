package com.oop2.tim6.NakitWebTim6.repository;

import java.util.List;

import com.oop2.tim6.NakitWebTim6.model.Komentar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IKomentarJpaRepositoryTim6 extends JpaRepository<Komentar, Integer> {
	@Query("SELECT k FROM Komentar k WHERE k.ogla.idOgla=:idOglas")
	public List<Komentar> getKomentariZaOglasId(@Param("idOglas") Integer idOgla);
}