package com.oop2.tim6.NakitWebTim6.converter;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.service.IKorisnikServiceTim6;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class IdToKorisnikConverter implements Converter<Integer,Korisnik> {

    IKorisnikServiceTim6 korisnikService;

    @Override
    public Korisnik convert(Integer integer) {
       return korisnikService.getKorisnikWithId(integer);
    }

    @Autowired
    public void setKorisnikService(IKorisnikServiceTim6 korisnikService) {
        this.korisnikService = korisnikService;
    }
}
