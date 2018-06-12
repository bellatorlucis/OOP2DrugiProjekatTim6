package com.oop2.tim6.NakitWebTim6.service;

import com.oop2.tim6.NakitWebTim6.repository.KorisnikRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService implements IHelloWordlService {

    private KorisnikRepo korisnikRepo;

    @Override
    public String getHelloMessage() {
        //Ilitracija rada sa servisima i repositorijumima
        //korisnikRepo.getSveKorisnike();
        return "Hello Hello Hello";
    }

    @Autowired
    public void setKorisnikRepo(KorisnikRepo korisnikRepo) {
        this.korisnikRepo = korisnikRepo;
    }
}
