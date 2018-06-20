package com.oop2.tim6.NakitWebTim6.controller;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.service.IKorisnikServiceTim6;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KorisnikControllerTim6 {

    private IKorisnikServiceTim6 korisnikService;

    @RequestMapping(value = "/korisnikSlika/{username}")
    @ResponseBody
    public byte[] getImageKorisnika(@PathVariable String username)  {

        Korisnik korisnik = korisnikService.getKorisnikWithUsername(username);

        return korisnik.getSlika();
    }

    @Autowired
    public void setKorisnikService(IKorisnikServiceTim6 korisnikService) {
        this.korisnikService = korisnikService;
    }
}
