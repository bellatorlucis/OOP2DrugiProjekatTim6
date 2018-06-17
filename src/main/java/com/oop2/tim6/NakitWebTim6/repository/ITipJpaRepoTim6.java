package com.oop2.tim6.NakitWebTim6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oop2.tim6.NakitWebTim6.model.Tip;

@Repository
public interface ITipJpaRepoTim6 extends JpaRepository<Tip, Integer> {
	
	public List<Tip> findAll();
}
