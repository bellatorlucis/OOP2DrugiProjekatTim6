package com.oop2.tim6.NakitWebTim6.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oop2.tim6.NakitWebTim6.model.Ogla;

@Repository
@Transactional
public class OglasFilterFindRepo {

    @PersistenceContext
    EntityManager entityManager;
	
	public List<Ogla> getOglasiBy(String query){
    	Query q = entityManager.createQuery("select o from Ogla o where "+query);
    	List<Ogla> oglasi = q.getResultList();
    	return oglasi;
    }
}
