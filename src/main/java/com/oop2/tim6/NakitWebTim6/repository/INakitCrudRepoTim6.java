package com.oop2.tim6.NakitWebTim6.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.oop2.tim6.NakitWebTim6.model.Nakit;

public interface INakitCrudRepoTim6 extends CrudRepository<Nakit, Integer> {
	@Query("SELECT n FROM Nakit n WHERE n.oglas.idOgla=:idO")
	public Nakit findByIdOglasa(@Param("idO") int idOglasa);
}
