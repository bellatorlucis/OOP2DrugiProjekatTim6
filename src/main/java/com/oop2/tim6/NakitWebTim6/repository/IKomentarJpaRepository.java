package com.oop2.tim6.NakitWebTim6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oop2.tim6.NakitWebTim6.model.Komentar;
import com.oop2.tim6.NakitWebTim6.model.Ponuda;

@Repository
public interface IKomentarJpaRepository extends JpaRepository<Komentar, Integer>, IKomentarSpecificRepository {
	@Query("SELECT k FROM Komentar k WHERE k.ogla.idOgla=:idOglas")
	public List<Komentar> getKomentariZaOglasId(@Param("idk") Integer idOglas);
}