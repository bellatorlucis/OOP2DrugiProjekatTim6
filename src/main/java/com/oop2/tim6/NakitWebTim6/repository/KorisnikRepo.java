package com.oop2.tim6.NakitWebTim6.repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class KorisnikRepo {

    @PersistenceContext
    EntityManager entityManager;
}
