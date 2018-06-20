package com.oop2.tim6.NakitWebTim6.service;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.repository.IKorisnikJpaRepoTim6;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KorisnikServiceTim6 implements IKorisnikServiceTim6 {


    private IKorisnikJpaRepoTim6 korisnikJpaRepo;


    @Override
    public Korisnik getKorisnikWithUsername(String username) {
       return  korisnikJpaRepo.findByKorisnickoIme(username);
    }

    @Override
    public Korisnik getKorisnikWithId(int id) {
       return korisnikJpaRepo.findByIdKorisnika(id);
    }

    @Autowired
    public void setKorisnikRepo(IKorisnikJpaRepoTim6 korisnikJpaRepo) {
        this.korisnikJpaRepo = korisnikJpaRepo;
    }
}
