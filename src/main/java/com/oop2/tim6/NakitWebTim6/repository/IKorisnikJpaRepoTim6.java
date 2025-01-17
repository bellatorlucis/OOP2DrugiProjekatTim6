package com.oop2.tim6.NakitWebTim6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;

@Repository
public interface IKorisnikJpaRepoTim6 extends JpaRepository<Korisnik, Integer> {
	List<Korisnik> findAll();
	Korisnik findByKorisnickoIme(String korisnickoIme);
	Korisnik findByIdKorisnika(int idKorisnika);
}