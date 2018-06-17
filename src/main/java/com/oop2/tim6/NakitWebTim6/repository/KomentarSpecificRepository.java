package com.oop2.tim6.NakitWebTim6.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.oop2.tim6.NakitWebTim6.model.Komentar;

public class KomentarSpecificRepository implements IKomentarSpecificRepository {

	@PersistenceContext
	private EntityManager em;	
	
	public Komentar dodajKomentar(Komentar k) {
		try
		{
			em.persist(k);
			em.flush();
			return k;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
}
