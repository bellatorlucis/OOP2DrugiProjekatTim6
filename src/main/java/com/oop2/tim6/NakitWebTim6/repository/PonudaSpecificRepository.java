package com.oop2.tim6.NakitWebTim6.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oop2.tim6.NakitWebTim6.model.Ponuda;

@Repository
@Transactional
public class PonudaSpecificRepository implements IPonudaSpecificRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public Ponuda dodajPonudu(Ponuda p) {
		try
		{
			em.persist(p);
			em.flush();
			return p;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
