package com.oop2.tim6.NakitWebTim6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oop2.tim6.NakitWebTim6.model.Ponuda;

@Repository
public interface PonudaJpaRepo extends JpaRepository<Ponuda, Integer> {
	
	
}