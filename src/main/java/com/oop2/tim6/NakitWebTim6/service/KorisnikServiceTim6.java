package com.oop2.tim6.NakitWebTim6.service;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.repository.IKorisnikJpaRepo;
import com.oop2.tim6.NakitWebTim6.repository.KorisnikRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KorisnikServiceTim6 implements IKorisnikService {


    private IKorisnikJpaRepo korisnikJpaRepo;


    @Override
    public Korisnik getKorisnikWithUsername(String username) {
       return  korisnikJpaRepo.findByKorisnickoIme(username);
    }

    @Autowired
    public void setKorisnikRepo(IKorisnikJpaRepo korisnikJpaRepo) {
        this.korisnikJpaRepo = korisnikJpaRepo;
    }
}
