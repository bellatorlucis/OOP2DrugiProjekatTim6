package com.oop2.tim6.NakitWebTim6.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class KorisnikRepoTim6 {

    @PersistenceContext
    EntityManager entityManager;

}
